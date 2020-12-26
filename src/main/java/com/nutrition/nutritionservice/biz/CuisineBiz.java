package com.nutrition.nutritionservice.biz;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.converter.ModelConverter;
import com.nutrition.nutritionservice.enums.database.IngredientCategoryEnum;
import com.nutrition.nutritionservice.service.CuisineService;
import com.nutrition.nutritionservice.service.DineRecommendedRateService;
import com.nutrition.nutritionservice.service.IngredientService;
import com.nutrition.nutritionservice.vo.DineRecommendedRateVo;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.nutrition.nutritionservice.vo.modeldata.IntakesModelVo;
import com.nutrition.nutritionservice.vo.store.CuisineAssemblyAo;
import com.nutrition.nutritionservice.vo.store.CuisineIngredientRelVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
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
    private IntakesModelBiz intakesModelBiz;

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
        Map<IngredientCategoryEnum, Integer> ingredientCategoryWeightMap = ModelConverter.INSTANCE
                .categoryWeightToMap(intakesModelVo);
        DineRecommendedRateVo dineRecommendedRateVo = dineRecommendedRateService
                .selectByCalorieGoalDine(intakesModelVo.getCalorie(), intakesModelVo.getGoal(), dineTime);
        Map<IngredientCategoryEnum, Double> dineRateMap = ModelConverter.INSTANCE
                .categoryRateToMap(dineRecommendedRateVo);
        Map<String, Integer> dineRecommendedWeightMap = Maps.newHashMap();
        ingredientCategoryWeightMap.forEach((key, value) -> {
            dineRecommendedWeightMap.put(key.getNameEn(), (int) (value * dineRateMap.getOrDefault(key, 0.0)));
        });
        return dineRecommendedWeightMap;
    }

}
