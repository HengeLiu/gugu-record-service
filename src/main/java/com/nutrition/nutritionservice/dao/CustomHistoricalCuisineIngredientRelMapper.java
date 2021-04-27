package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.CustomHistoricalCuisineIngredientRelVo;

import java.util.List;

public interface CustomHistoricalCuisineIngredientRelMapper {

    int batchInsert(List<CustomHistoricalCuisineIngredientRelVo> relList);

    List<CustomHistoricalCuisineIngredientRelVo> selectByHistoricalCuisineId(long userHistoricalCuisineId);

    int deleteByHistoricalCuisineId(long userHistoricalCuisineId);

}