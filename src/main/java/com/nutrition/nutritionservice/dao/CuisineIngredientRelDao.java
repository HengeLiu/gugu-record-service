package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.CuisineIngredientRelVo;

import java.util.List;

public interface CuisineIngredientRelDao {

    int insert(CuisineIngredientRelVo record);

    int batchInsert(List<CuisineIngredientRelVo> relList);

    int insertSelective(CuisineIngredientRelVo record);

    List<CuisineIngredientRelVo> selectByCuisineCode(String cuisineCode);

    List<CuisineIngredientRelVo> batchSelectByCuisineCodeList(List<String> cuisineCodeList);

    int deleteByCuisineCode(String cuisineCode);
}