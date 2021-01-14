package com.nutrition.nutritionservice.biz.health;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.controller.health.ao.CuisineCategoryAo;
import com.nutrition.nutritionservice.controller.health.ao.CuisineDesignerAo;
import com.nutrition.nutritionservice.controller.health.ao.CuisineDetailsAo;
import com.nutrition.nutritionservice.controller.health.ao.CuisineIngredientAo;
import com.nutrition.nutritionservice.controller.ao.CuisinePreviewAo;
import com.nutrition.nutritionservice.controller.health.ao.StoreCuisineListAo;
import com.nutrition.nutritionservice.controller.health.ao.StorePreviewAo;
import com.nutrition.nutritionservice.converter.NutrientWeightVo2AoConverter;
import com.nutrition.nutritionservice.enums.CodeEnums;
import com.nutrition.nutritionservice.enums.UnitEnum;
import com.nutrition.nutritionservice.enums.database.CuisineCategoryEnum;
import com.nutrition.nutritionservice.enums.database.CuisineTasteEnum;
import com.nutrition.nutritionservice.enums.database.IngredientCategoryEnum;
import com.nutrition.nutritionservice.exception.NutritionServiceException;
import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import com.nutrition.nutritionservice.service.CuisineAdditionalInfoService;
import com.nutrition.nutritionservice.service.CuisineHistoricalTasteService;
import com.nutrition.nutritionservice.service.CuisineIngredientCategoryWeightService;
import com.nutrition.nutritionservice.service.CuisineIngredientRelService;
import com.nutrition.nutritionservice.service.CuisineNutrientWeightService;
import com.nutrition.nutritionservice.service.CuisineService;
import com.nutrition.nutritionservice.service.DineRecommendedRateService;
import com.nutrition.nutritionservice.service.IngredientNutrientRelService;
import com.nutrition.nutritionservice.service.IngredientService;
import com.nutrition.nutritionservice.service.StoreService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.service.UserIngredientCategoryModelService;
import com.nutrition.nutritionservice.service.UserIngredientWeightSumDailyService;
import com.nutrition.nutritionservice.util.ModelUtil;
import com.nutrition.nutritionservice.util.VectorUtil;
import com.nutrition.nutritionservice.vo.CuisineAdditionalInfoVo;
import com.nutrition.nutritionservice.vo.CuisineHistoricalTasteVo;
import com.nutrition.nutritionservice.vo.CuisineIngredientCategoryWeightVo;
import com.nutrition.nutritionservice.vo.CuisineNutrientWeightVo;
import com.nutrition.nutritionservice.vo.CuisineRecommendedScoreWebAo;
import com.nutrition.nutritionservice.vo.DineRecommendedRateVo;
import com.nutrition.nutritionservice.vo.IDPageParamVo;
import com.nutrition.nutritionservice.vo.IngredientNutrientRelVo;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.nutrition.nutritionservice.vo.StoreVo;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientCategoryModelVo;
import com.nutrition.nutritionservice.vo.store.CuisineIngredientRelVo;
import com.nutrition.nutritionservice.vo.store.CuisineVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientCategoryModelVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientWeightSumDailyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.ListUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.Vector;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 菜品。
 * 
 * @author heng.liu
 * @since 2020/12/23
 */
@Biz
@Slf4j
public class CuisineBiz {

    @Resource
    private CuisineService cuisineService;

    @Resource
    private IngredientService ingredientService;

    @Resource
    private DineRecommendedRateService dineRecommendedRateService;

    @Resource
    private CuisineIngredientCategoryWeightService cuisineIngredientCategoryWeightService;

    @Resource
    private CuisineNutrientWeightService cuisineNutrientWeightService;

    @Resource
    private CuisineHistoricalTasteService cuisineHistoricalTasteService;

    @Resource
    private CuisineIngredientRelService cuisineIngredientRelService;

    @Resource
    private ConfigPropertiesService configPropertiesService;

    @Resource
    private CuisineAdditionalInfoService cuisineAdditionalInfoService;

    @Resource
    private IngredientBiz ingredientBiz;

    @Resource
    private StoreService storeService;

    @Resource
    private UserIngredientCategoryModelService userIngredientCategoryModelService;

    @Resource
    private UserIngredientWeightSumDailyService userIngredientWeightSumDailyService;

    @Resource
    private UserInfoService userInfoService;



    @Resource
    private IngredientNutrientRelService ingredientNutrientRelService;

    @Transactional(rollbackFor = Exception.class)
    public void addNewCuisine(CuisineDesignerAo cuisineDesignerAo) {
        String cuisineCode = UUID.randomUUID().toString().replace("-", "");
        List<CuisineIngredientRelVo> cuisineIngredientRelList = cuisineDesignerAo.getCuisineIngredientRelList();
        if (CollectionUtils.isEmpty(cuisineIngredientRelList)) {
            throw new NutritionServiceException("New cuisine ingredient list can not be empty, saving new cuisine.");
        }
        // 计算菜品热量
        double cuisineCalorie = ingredientBiz.calculateCalorie(cuisineIngredientRelList);
        cuisineDesignerAo.getCuisineVo().setCode(cuisineCode);
        cuisineDesignerAo.getCuisineVo().setCalorie(cuisineCalorie);
        // 保存菜品基础信息
        cuisineService.add(cuisineDesignerAo.getCuisineVo());
        cuisineIngredientRelList
                .forEach(cuisineIngredientRelVo -> cuisineIngredientRelVo.setCuisineCode(cuisineCode));
        // 保存菜品食材重量信息
        cuisineIngredientRelService.addBatch(cuisineIngredientRelList);
        CuisineIngredientCategoryWeightVo cuisineIngredientCategoryWeightVo = cuisineIngredientCategoryWeightService
                .calculateCuisineCategoryWight(cuisineDesignerAo);
        // 保存菜品食材分类重量信息
        cuisineIngredientCategoryWeightService.add(cuisineIngredientCategoryWeightVo);
        // 保存菜品营养素重量信息
        cuisineNutrientWeightService.addAll(this.calculateNutrientWeight(cuisineIngredientRelList, cuisineCode));
    }

    public List<CuisineNutrientWeightVo> calculateNutrientWeight(
            List<CuisineIngredientRelVo> cuisineIngredientRelVoList, String cuisineCode) {
        // 餐品食材重量
        Map<Integer, Integer> ingredientCodeWeightMap = cuisineIngredientRelVoList.stream().collect(
                Collectors.toMap(CuisineIngredientRelVo::getIngredientCode, CuisineIngredientRelVo::getWeight));
        // 食材营养素含量
        Map<Integer, List<IngredientNutrientRelVo>> ingredientNutrientMap = ingredientNutrientRelService
                .queryByIngredientCodeList(Lists.newArrayList(ingredientCodeWeightMap.keySet())).stream()
                .collect(Collectors.groupingBy(IngredientNutrientRelVo::getIngredientCode));
        Map<Integer, Double> nutrientCodeWeightMap = Maps.newHashMap();
        for (Map.Entry<Integer, Integer> ingredientWeightEntry : ingredientCodeWeightMap.entrySet()) {
            Integer ingredientCode = ingredientWeightEntry.getKey();
            Integer ingredientWeight = ingredientWeightEntry.getValue();
            List<IngredientNutrientRelVo> ingredientNutrientRelList = ingredientNutrientMap.get(ingredientCode);
            if (CollectionUtils.isEmpty(ingredientNutrientRelList)) {
                log.error("Ingredient nutrient info is empty {}", ingredientCode);
                continue;
            }
            for (IngredientNutrientRelVo ingredientNutrientRelVo : ingredientNutrientRelList) {
                if ("none".equals(ingredientNutrientRelVo.getNutrientContent())
                        || "tr".equals(ingredientNutrientRelVo.getNutrientContent())
                        || UnitEnum.PERCENT.getName().equals(ingredientNutrientRelVo.getContentUnit())) {
                    continue;
                }
                double ingredientNutrientWeight = Double.parseDouble(ingredientNutrientRelVo.getNutrientContent())
                        * ingredientWeight / 100;
                if (UnitEnum.MG.getName().equals(ingredientNutrientRelVo.getContentUnit())) {
                    ingredientNutrientWeight /= 1000;
                }
                nutrientCodeWeightMap.put(ingredientNutrientRelVo.getNutrientCode(), ingredientNutrientWeight
                        + nutrientCodeWeightMap.getOrDefault(ingredientNutrientRelVo.getNutrientCode(), 0.0));
            }
        }
        return nutrientCodeWeightMap.entrySet().stream()
                .map(nutrientCodeWeightEntry -> CuisineNutrientWeightVo.builder().cuisineCode(cuisineCode)
                        .nutrientCode(nutrientCodeWeightEntry.getKey()).weight(nutrientCodeWeightEntry.getValue())
                        .build())
                .collect(Collectors.toList());
    }

    public Map<String, List<IngredientVo>> queryIngredientCategoryMap() {
        Map<String, List<IngredientVo>> ingredientCategoryMap = Maps.newHashMap();
        for (IngredientCategoryEnum categoryEnum : IngredientCategoryEnum.values()) {
            ingredientCategoryMap.put(categoryEnum.getNameEn(),
                    ingredientService.queryByCategoryCode(categoryEnum.getCode()));
        }
        return ingredientCategoryMap;
    }

    public Map<String, Integer> queryRecommendedCategoryWeightMap(ModelIngredientCategoryModelVo modelIngredientCategoryModelVo, int dineTime) {
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        Map<IngredientCategoryEnum, Integer> ingredientCategoryWeightMap = ModelUtil
                .modelToCategoryEnumMap(modelIngredientCategoryModelVo);
        DineRecommendedRateVo dineRecommendedRateVo = dineRecommendedRateService
                .selectByCalorieGoalDine(modelIngredientCategoryModelVo.getStandardCalorie(),
                        modelIngredientCategoryModelVo.getGoal(), dineTime);
        Map<IngredientCategoryEnum, Double> dineRateMap = ModelUtil.modelToCategoryEnumMap(dineRecommendedRateVo);
        Map<String, Integer> dineRecommendedWeightMap = Maps.newHashMap();
        ingredientCategoryWeightMap.forEach((key, value) -> dineRecommendedWeightMap.put(key.getNameEn(),
                (int) (value * dineRateMap.getOrDefault(key, 0.0))));
        return dineRecommendedWeightMap;
    }

    public List<CuisineRecommendedScoreWebAo> queryRecommendedCuisineListByDineTime(
            UserIngredientCategoryModelVo userModel, int dineTime, IDPageParamVo pageParamVo) {
        Vector<Integer> userModelVector = ModelUtil.modelToVector(userModel);
        DineRecommendedRateVo dineRecommendedRateVo = dineRecommendedRateService
                .selectByCalorieGoalDine(userModel.getTargetCalorie(), userModel.getGoal(), dineTime);
        Vector<Double> dineRateVector = ModelUtil.modelToVector(dineRecommendedRateVo);
        Vector<Double> recommendedWeightVector = VectorUtil.crossProduct(userModelVector, dineRateVector);
        List<CuisineVo> cuisineList = cuisineService.queryPage(pageParamVo);
        List<CuisineIngredientCategoryWeightVo> cuisineCategoryWeightList = cuisineIngredientCategoryWeightService
                .queryByCuisineCodeList(cuisineList.stream().map(CuisineVo::getCode).collect(Collectors.toList()));
        Map<String, Double> modelSimilarityMap = cuisineCategoryWeightList.stream()
                .collect(Collectors.toMap(CuisineIngredientCategoryWeightVo::getCuisineCode,
                        cuisineCategoryWeight -> VectorUtil
                        .cosineSimilarity(recommendedWeightVector, ModelUtil.modelToVector(cuisineCategoryWeight))));
        return calculateRecommendedScore(cuisineList, modelSimilarityMap);
    }

    public List<CuisineRecommendedScoreWebAo> queryRecommendedCuisineListByHistorical(
            UserIngredientCategoryModelVo userModel, UserIngredientWeightSumDailyVo historicalWeightSumDaily,
            IDPageParamVo pageParamVo) {
        Vector<Integer> userModelVector = ModelUtil.modelToVector(userModel);
        Vector<Double> historicalWeightVector = ModelUtil.modelToVector(historicalWeightSumDaily);
        Vector<Double> subtractionVector = VectorUtil.subtraction(userModelVector, historicalWeightVector);
        List<CuisineVo> cuisineList = cuisineService.queryPage(pageParamVo);
        List<CuisineIngredientCategoryWeightVo> cuisineCategoryWeightList = cuisineIngredientCategoryWeightService
                .queryByCuisineCodeList(cuisineList.stream().map(CuisineVo::getCode).collect(Collectors.toList()));
        Map<String, Double> modelSimilarityMap = cuisineCategoryWeightList.stream()
                .collect(Collectors.toMap(CuisineIngredientCategoryWeightVo::getCuisineCode,
                        cuisineCategoryWeight -> VectorUtil
                        .cosineSimilarity(subtractionVector, ModelUtil.modelToVector(cuisineCategoryWeight))));
        return calculateRecommendedScore(cuisineList, modelSimilarityMap);
    }

    private List<CuisineRecommendedScoreWebAo> calculateRecommendedScore(List<CuisineVo> cuisineList,
            Map<String, Double> modelSimilarityMap) {
        Map<String, Double> cuisineTasteScore = calculateTasteScore(cuisineList);
        double modelRate = configPropertiesService.getRecommendedCuisineModelRate();
        double tasteRate = configPropertiesService.getRecommendedCuisineTasteRate();
        return cuisineList.stream()
                .map(cuisineVo -> CuisineRecommendedScoreWebAo.builder().cuisineCode(cuisineVo.getCode())
                        .recommendedScore(modelRate * modelSimilarityMap.get(cuisineVo.getCode())
                                + tasteRate * cuisineTasteScore.getOrDefault(cuisineVo.getCode(), 0.0))
                        .modelScore(modelSimilarityMap.get(cuisineVo.getCode()))
                        .tasteScore(cuisineTasteScore.getOrDefault(cuisineVo.getCode(), 0.0)).build())
                .sorted(Comparator.comparingDouble(CuisineRecommendedScoreWebAo::getRecommendedScore).reversed())
                .collect(Collectors.toList());
    }

    private Map<String, Double> calculateTasteScore(List<CuisineVo> cuisineVoList) {
        List<CuisineHistoricalTasteVo> cuisineHistoricalTasteVoList = cuisineHistoricalTasteService
                .queryByCuisineCodeList(cuisineVoList.stream().map(CuisineVo::getCode).collect(Collectors.toList()));
        if (ListUtils.isEmpty(cuisineHistoricalTasteVoList)) {
            return Collections.emptyMap();
        }
        Map<String, Long> cuisineTasteCountMap = cuisineHistoricalTasteVoList.stream()
                .collect(Collectors.groupingBy(CuisineHistoricalTasteVo::getCuisineCode, Collectors.counting()));
        Map<String, Integer> cuisineTasteOriginScoreMap = cuisineHistoricalTasteVoList.stream()
                .collect(Collectors.groupingBy(CuisineHistoricalTasteVo::getCuisineCode,
                        Collectors.summingInt(cuisineTasteVo -> Objects
                                .requireNonNull(CodeEnums.valueOf(CuisineTasteEnum.class, cuisineTasteVo.getTaste()))
                                .getScore() * cuisineTasteVo.getCount())));
        Map<String, Double> cuisineTasteScorePercentMap = cuisineTasteOriginScoreMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue() / (double) cuisineTasteCountMap.get(entry.getKey())));
        double maxScorePercent = cuisineTasteScorePercentMap.values().stream()
                .max(Comparator.comparingDouble(Double::doubleValue)).orElse(0.0);
        return cuisineTasteScorePercentMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue() / maxScorePercent));

    }

    public StoreCuisineListAo queryCuisineList(String storeCode) {
        StoreCuisineListAo.StoreCuisineListAoBuilder storeCuisineListAoBuilder = StoreCuisineListAo.builder();
        StoreVo storeVo = storeService.queryByCode(storeCode);
        if (storeVo == null) {
            throw new NutritionServiceException("门店未找到，storeCode " + storeCode);
        }
        storeCuisineListAoBuilder.storeCode(storeCode);
        storeCuisineListAoBuilder.storeName(storeVo.getName());

        List<CuisineVo> cuisineVoList = cuisineService.queryByStoreCode(storeCode);
        if (CollectionUtils.isEmpty(cuisineVoList)) {
            storeCuisineListAoBuilder.cuisineCategoryList(Collections.emptyList());
            return storeCuisineListAoBuilder.build();
        }
        List<CuisineIngredientRelVo> cuisineIngredientRelVoList = cuisineIngredientRelService
                .queryByCuisineCodeList(cuisineVoList.stream().map(CuisineVo::getCode).collect(Collectors.toList()));
        List<IngredientVo> ingredientVoList = ingredientService.queryByCodeList(cuisineIngredientRelVoList.stream()
                .map(CuisineIngredientRelVo::getIngredientCode).distinct().collect(Collectors.toList()));
        Map<Integer, String> ingredientCodeNameMap = ingredientVoList.stream()
                .collect(Collectors.toMap(IngredientVo::getCode, IngredientVo::getName));
        // 餐品食材名称列表
        Map<String, List<String>> cuisineCodeIngredientNameMap = cuisineIngredientRelVoList.stream()
                .collect(Collectors.groupingBy(CuisineIngredientRelVo::getCuisineCode)).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .sorted(Comparator.comparingInt(CuisineIngredientRelVo::getWeight).reversed())
                                .map(cuisineIngredientRelVo -> ingredientCodeNameMap
                                        .get(cuisineIngredientRelVo.getIngredientCode()))
                                .collect(Collectors.toList())));
        Map<CuisineCategoryEnum, List<CuisineVo>> cuisineCategoryMap = cuisineVoList.stream().collect(Collectors
                .groupingBy(cuisine -> CodeEnums.valueOf(CuisineCategoryEnum.class, cuisine.getCuisineType())));
        // 餐品分类列表
        List<CuisineCategoryAo> cuisineCategoryAoList = cuisineCategoryMap.entrySet().stream()
                .map(cuisineCategoryEnumListEntry -> CuisineCategoryAo.builder()
                        .categoryCode(cuisineCategoryEnumListEntry.getKey().getCode())
                        .categoryName(cuisineCategoryEnumListEntry.getKey().getName())
                        .cuisineList(cuisineCategoryEnumListEntry.getValue().stream()
                                .map(cuisineVo -> CuisinePreviewAo.builder().code(cuisineVo.getCode())
                                        .name(cuisineVo.getName()).imageUrl(cuisineVo.getImageUrl())
                                        .mainIngredientList(cuisineCodeIngredientNameMap
                                                .getOrDefault(cuisineVo.getCode(), Collections.emptyList()))
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        return storeCuisineListAoBuilder.cuisineCategoryList(cuisineCategoryAoList).build();
    }

    public CuisineDetailsAo queryCuisineDetails(String cuisineCode, String uuid) {

        CuisineDetailsAo.CuisineDetailsAoBuilder cuisineDetailsAoBuilder = CuisineDetailsAo.builder();

        cuisineDetailsAoBuilder.code(cuisineCode);
        CuisineVo cuisineVo = cuisineService.queryByCuisineCode(cuisineCode);
        cuisineDetailsAoBuilder.name(cuisineVo.getName());
        CuisineAdditionalInfoVo cuisineAdditionalInfoVo = cuisineAdditionalInfoService.queryByCuisineCode(cuisineCode);
        if (cuisineAdditionalInfoVo != null) {
            cuisineDetailsAoBuilder.price(cuisineAdditionalInfoVo.getPrice());
            cuisineDetailsAoBuilder.ratingScore(cuisineAdditionalInfoVo.getRatingScore());
            cuisineDetailsAoBuilder.eleUrl(cuisineAdditionalInfoVo.getEleUrl());
            cuisineDetailsAoBuilder.meituanUrl(cuisineAdditionalInfoVo.getMeituanUrl());
            cuisineDetailsAoBuilder.imageUrl(cuisineAdditionalInfoVo.getImageUrl());
        }

        UserInfoVo userInfoVo = userInfoService.selectByUuid(uuid);
        UserIngredientCategoryModelVo userIngredientCategoryModelVo = userIngredientCategoryModelService
                .queryById(userInfoVo.getActiveModelId());
        UserIngredientWeightSumDailyVo userIngredientWeightSumDailyVo = userIngredientWeightSumDailyService
                .queryByUuidAndDate(uuid, LocalDate.now());
        if (userIngredientWeightSumDailyVo == null) {
            userIngredientWeightSumDailyVo = UserIngredientWeightSumDailyVo.createEmpty(uuid, LocalDate.now());
        }
        Vector<Integer> userModelVector = ModelUtil.modelToVector(userIngredientCategoryModelVo);
        Vector<Double> historicalWeightVector = ModelUtil.modelToVector(userIngredientWeightSumDailyVo);
        Vector<Double> subtractionVector = VectorUtil.subtraction(userModelVector, historicalWeightVector);

        CuisineIngredientCategoryWeightVo cuisineIngredientCategoryWeightVo = cuisineIngredientCategoryWeightService
                .queryByCuisineCode(cuisineCode);
        if (cuisineIngredientCategoryWeightVo == null) {
            throw new NutritionServiceException(
                    "Cuisine ingredient category weight can not null, cuisine code " + cuisineCode);
        }
        double cosineSimilarity = VectorUtil.cosineSimilarity(subtractionVector,
                ModelUtil.modelToVector(cuisineIngredientCategoryWeightVo));
        cuisineDetailsAoBuilder.modelMatchingScore(cosineSimilarity);

        Map<Integer, Integer> ingredientCodeWeightMap = cuisineIngredientRelService.queryByCuisineCode(cuisineCode)
                .stream().filter(cuisineIngredientRelVo -> cuisineIngredientRelVo.getWeight() > 0).collect(
                        Collectors.toMap(CuisineIngredientRelVo::getIngredientCode, CuisineIngredientRelVo::getWeight));
        Map<Integer, IngredientVo> ingredientCodeMap = ingredientService
                .queryByCodeList(Lists.newArrayList(ingredientCodeWeightMap.keySet())).stream()
                .collect(Collectors.toMap(IngredientVo::getCode, Function.identity()));
        List<CuisineIngredientAo> cuisineIngredientAoList = Lists.newArrayList();
        for (Map.Entry<Integer, Integer> cuisineIngredientWeightEntry : ingredientCodeWeightMap.entrySet()) {
            Integer ingredientCode = cuisineIngredientWeightEntry.getKey();
            IngredientVo ingredientVo = ingredientCodeMap.get(ingredientCode);
            if (ingredientVo == null) {
                log.error("Ingredient not exist, code {}", ingredientCode);
                continue;
            }
            cuisineIngredientAoList.add(CuisineIngredientAo.builder().code(ingredientCode).name(ingredientVo.getName())
                    .weight(cuisineIngredientWeightEntry.getValue()).imageUrl(ingredientVo.getImageUrl()).build());
        }
        cuisineDetailsAoBuilder.ingredientList(cuisineIngredientAoList);

        cuisineDetailsAoBuilder.ingredientCategoryList(
                ModelUtil.cuisineIngredientCategoryWeightVoToAo(cuisineIngredientCategoryWeightVo));

        List<CuisineNutrientWeightVo> cuisineNutrientWeightVoList = cuisineNutrientWeightService
                .queryByCuisineCode(cuisineCode);
        cuisineDetailsAoBuilder
                .nutrientWeightList(NutrientWeightVo2AoConverter.convert(null, cuisineNutrientWeightVoList));

        StoreVo storeVo = storeService.queryByCode(cuisineVo.getStoreCode());
        if (storeVo == null) {
            throw new NutritionServiceException("Cuisine store can not null, cuisine code " + cuisineCode
                    + ", store code " + cuisineVo.getStoreCode());
        }
        cuisineDetailsAoBuilder
                .storeInfo(StorePreviewAo.builder().code(storeVo.getCode()).name(storeVo.getName()).build());

        return cuisineDetailsAoBuilder.build();
    }
}
