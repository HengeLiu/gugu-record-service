package com.nutrition.nutritionservice.biz;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.enums.CodeEnums;
import com.nutrition.nutritionservice.enums.database.CuisineTasteEnum;
import com.nutrition.nutritionservice.enums.database.IngredientCategoryEnum;
import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import com.nutrition.nutritionservice.service.CuisineCategoryWeightService;
import com.nutrition.nutritionservice.service.CuisineHistoricalTasteService;
import com.nutrition.nutritionservice.service.CuisineService;
import com.nutrition.nutritionservice.service.DineRecommendedRateService;
import com.nutrition.nutritionservice.service.IngredientService;
import com.nutrition.nutritionservice.util.ModelUtil;
import com.nutrition.nutritionservice.util.VectorUtil;
import com.nutrition.nutritionservice.vo.CuisineCategoryWeightVo;
import com.nutrition.nutritionservice.vo.CuisineHistoricalTasteVo;
import com.nutrition.nutritionservice.vo.CuisineRecommendedScoreWebAo;
import com.nutrition.nutritionservice.vo.DineRecommendedRateVo;
import com.nutrition.nutritionservice.vo.IDPageParamVo;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.nutrition.nutritionservice.vo.UserHistoricalWeightSumDailyVo;
import com.nutrition.nutritionservice.vo.modeldata.IntakesModelVo;
import com.nutrition.nutritionservice.vo.store.CuisineAssemblyAo;
import com.nutrition.nutritionservice.vo.store.CuisineVo;
import com.nutrition.nutritionservice.vo.user.UserCategoryIntakesModelVo;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * 菜品。
 * 
 * @author heng.liu
 * @since 2020/12/23
 */
@Biz
public class CuisineBiz {

    @Resource
    private CuisineService cuisineService;

    @Resource
    private IngredientService ingredientService;

    @Resource
    private DineRecommendedRateService dineRecommendedRateService;

    @Resource
    private CuisineCategoryWeightService cuisineCategoryWeightService;

    @Resource
    private CuisineHistoricalTasteService cuisineHistoricalTasteService;

    @Resource
    private ConfigPropertiesService configPropertiesService;

    public void saveNewCuisine(CuisineAssemblyAo cuisineAssemblyAo) {
        cuisineService.saveNewCuisine(cuisineAssemblyAo);
    }

    public Map<String, List<IngredientVo>> queryIngredientCategoryMap() {
        Map<String, List<IngredientVo>> ingredientCategoryMap = Maps.newHashMap();
        for (IngredientCategoryEnum categoryEnum : IngredientCategoryEnum.values()) {
            ingredientCategoryMap.put(categoryEnum.getNameEn(),
                    ingredientService.queryByCategoryCode(categoryEnum.getCode()));
        }
        return ingredientCategoryMap;
    }

    public Map<String, Integer> queryRecommendedCategoryWeightMap(IntakesModelVo intakesModelVo, int dineTime) {
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        Map<IngredientCategoryEnum, Integer> ingredientCategoryWeightMap = ModelUtil
                .modelToCategoryEnumMap(intakesModelVo);
        DineRecommendedRateVo dineRecommendedRateVo = dineRecommendedRateService
                .selectByCalorieGoalDine(intakesModelVo.getCalorie(), intakesModelVo.getGoal(), dineTime);
        Map<IngredientCategoryEnum, Double> dineRateMap = ModelUtil.modelToCategoryEnumMap(dineRecommendedRateVo);
        Map<String, Integer> dineRecommendedWeightMap = Maps.newHashMap();
        ingredientCategoryWeightMap.forEach((key, value) -> dineRecommendedWeightMap.put(key.getNameEn(),
                (int) (value * dineRateMap.getOrDefault(key, 0.0))));
        return dineRecommendedWeightMap;
    }

    public List<CuisineRecommendedScoreWebAo> queryRecommendedCuisineListByDineTime(
            UserCategoryIntakesModelVo userModel, int dineTime, IDPageParamVo pageParamVo) {
        Vector<Integer> userModelVector = ModelUtil.modelToVector(userModel);
        DineRecommendedRateVo dineRecommendedRateVo = dineRecommendedRateService
                .selectByCalorieGoalDine(userModel.getCalorie(), userModel.getGoal(), dineTime);
        Vector<Double> dineRateVector = ModelUtil.modelToVector(dineRecommendedRateVo);
        Vector<Double> recommendedWeightVector = VectorUtil.crossProduct(userModelVector, dineRateVector);
        List<CuisineVo> cuisineList = cuisineService.queryPageCuisineList(pageParamVo);
        List<CuisineCategoryWeightVo> cuisineCategoryWeightList = cuisineCategoryWeightService
                .queryCategoryWeightList(cuisineList.stream().map(CuisineVo::getCode).collect(Collectors.toList()));
        Map<String, Double> modelSimilarityMap = cuisineCategoryWeightList.stream()
                .collect(Collectors.toMap(CuisineCategoryWeightVo::getCuisineCode, cuisineCategoryWeight -> VectorUtil
                        .cosineSimilarity(recommendedWeightVector, ModelUtil.modelToVector(cuisineCategoryWeight))));
        return calculateScore(cuisineList, modelSimilarityMap);
    }

    public List<CuisineRecommendedScoreWebAo> queryRecommendedCuisineListByHistorical(
            UserCategoryIntakesModelVo userModel, UserHistoricalWeightSumDailyVo historicalWeightSumDaily,
            IDPageParamVo pageParamVo) {
        Vector<Integer> userModelVector = ModelUtil.modelToVector(userModel);
        Vector<Integer> historicalWeightVector = ModelUtil.modelToVector(historicalWeightSumDaily);
        Vector<Double> subtractionVector = VectorUtil.subtraction(userModelVector, historicalWeightVector);
        List<CuisineVo> cuisineList = cuisineService.queryPageCuisineList(pageParamVo);
        List<CuisineCategoryWeightVo> cuisineCategoryWeightList = cuisineCategoryWeightService
                .queryCategoryWeightList(cuisineList.stream().map(CuisineVo::getCode).collect(Collectors.toList()));
        Map<String, Double> modelSimilarityMap = cuisineCategoryWeightList.stream()
                .collect(Collectors.toMap(CuisineCategoryWeightVo::getCuisineCode, cuisineCategoryWeight -> VectorUtil
                        .cosineSimilarity(subtractionVector, ModelUtil.modelToVector(cuisineCategoryWeight))));
        return calculateScore(cuisineList, modelSimilarityMap);
    }

    private List<CuisineRecommendedScoreWebAo> calculateScore(List<CuisineVo> cuisineList,
            Map<String, Double> modelSimilarityMap) {
        Map<String, Double> cuisineTasteScore = calculateTasteScore(cuisineList);
        double modelRate = configPropertiesService.getRecommendedCuisineModelRate();
        double tasteRate = configPropertiesService.getRecommendedCuisineTasteRate();
        return cuisineList.stream()
                .map(cuisineVo -> CuisineRecommendedScoreWebAo.builder()
                        .recommendedScore(modelRate * modelSimilarityMap.get(cuisineVo.getCode())
                                + tasteRate * cuisineTasteScore.get(cuisineVo.getCode()))
                        .modelScore(modelSimilarityMap.get(cuisineVo.getCode()))
                        .tasteScore(cuisineTasteScore.get(cuisineVo.getCode())).build())
                .collect(Collectors.toList());
    }

    private Map<String, Double> calculateTasteScore(List<CuisineVo> cuisineVoList) {
        List<CuisineHistoricalTasteVo> cuisineHistoricalTasteVoList = cuisineHistoricalTasteService
                .queryByCuisineCodeList(cuisineVoList.stream().map(CuisineVo::getCode).collect(Collectors.toList()));
        Map<String, Integer> cuisineTasteOriginScoreMap = cuisineHistoricalTasteVoList.stream()
                .collect(Collectors.groupingBy(CuisineHistoricalTasteVo::getCuisineCode,
                        Collectors.summingInt(cuisineTasteVo -> Objects
                                .requireNonNull(CodeEnums.valueOf(CuisineTasteEnum.class, cuisineTasteVo.getTaste()))
                                .getScore() * cuisineTasteVo.getCount())));
        double maxScore = cuisineTasteOriginScoreMap.values().stream().max(Comparator.comparingInt(Integer::intValue))
                .orElse(0);
        return cuisineTasteOriginScoreMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue() / maxScore));

    }
}
