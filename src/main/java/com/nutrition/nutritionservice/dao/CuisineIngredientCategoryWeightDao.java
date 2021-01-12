package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.CuisineIngredientCategoryWeightVo;

import java.util.List;

public interface CuisineIngredientCategoryWeightDao {

    List<CuisineIngredientCategoryWeightVo> batchSelectByCuisineCodeList(List<String> cuisineCodeList);

    int insert(CuisineIngredientCategoryWeightVo record);

    CuisineIngredientCategoryWeightVo selectByCuisineCode(String cuisineCode);

    int updateByCuisineCodeSelective(CuisineIngredientCategoryWeightVo record);

    int updateByCuisineCode(CuisineIngredientCategoryWeightVo record);
}