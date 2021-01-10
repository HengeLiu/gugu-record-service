package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.IngredientNutrientRelVo;

import java.util.List;

public interface IngredientNutrientRelDao {

    List<IngredientNutrientRelVo> selectByIngredientCode(int ingredientCode);

    List<IngredientNutrientRelVo> selectByIngredientCodeList(List<Integer> ingredientCodeList);

    List<IngredientNutrientRelVo> selectByIngredientCodeAndNutrientCode(int ingredientCode, int nutrientCode);

}