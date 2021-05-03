package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.CustomCuisineIngredientRelTmpVo;

import java.util.List;

public interface CustomCuisineIngredientRelTmpDao {

    int insert(CustomCuisineIngredientRelTmpVo record);

    int batchInsert(List<CustomCuisineIngredientRelTmpVo> relList);

    CustomCuisineIngredientRelTmpVo selectByCuisineCode(Integer id);

}