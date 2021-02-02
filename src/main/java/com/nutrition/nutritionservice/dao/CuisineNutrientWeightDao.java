package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.CuisineNutrientWeightVo;

import java.util.List;

public interface CuisineNutrientWeightDao {

    int insert(CuisineNutrientWeightVo record);

    int batchInsert(List<CuisineNutrientWeightVo> recordList);

    List<CuisineNutrientWeightVo> selectByCuisineCode(String cuisineCode);

    int updateWeightByCuisineAndNutrientCode(String cuisineCode, int nutrientCode, double weight);

    void deleteByCuisineCode(String cuisineCode);
}