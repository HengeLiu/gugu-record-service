package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Lists;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.controller.ao.IngredientAo;
import com.nutrition.nutritionservice.converter.IngredientVo2AoConverter;
import com.nutrition.nutritionservice.service.IngredientService;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.nutrition.nutritionservice.vo.CuisineIngredientRelVo;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 食材业务。
 * 
 * @author heng.liu
 * @since 2020/12/26
 */
@Biz
@Slf4j
public class IngredientBiz {

    @Resource
    private IngredientService ingredientService;

    public double calculateCalorieByIngredientWeight(Map<Integer, Integer> ingredientWeightMap) {
        List<IngredientVo> ingredientVoList = ingredientService
                .queryByCodeList(Lists.newArrayList(ingredientWeightMap.keySet()));
        double cuisineCalorie = 0;
        for (IngredientVo ingredient : ingredientVoList) {
            cuisineCalorie += (ingredientWeightMap.getOrDefault(ingredient.getCode(), 0) / 100.0)
                    * ingredient.getCalorie();
        }
        return cuisineCalorie;
    }

    public double calculateCalorie(List<CuisineIngredientRelVo> cuisineIngredientRelList) {
        Map<Integer, Integer> ingredientWeightMap = cuisineIngredientRelList.stream().collect(
                Collectors.toMap(CuisineIngredientRelVo::getIngredientCode, CuisineIngredientRelVo::getWeight));
        return this.calculateCalorieByIngredientWeight(ingredientWeightMap);
    }

    public List<IngredientAo> queryAvailable() {
        List<IngredientAo> ingredientAoList = IngredientVo2AoConverter
                .convertToList(ingredientService.queryAvailable());
        return ingredientAoList.stream().sorted(Comparator.comparing(IngredientAo::getFirstPinyin))
                .collect(Collectors.toList());
    }

}
