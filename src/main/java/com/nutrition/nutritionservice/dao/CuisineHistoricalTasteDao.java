package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.CuisineHistoricalTasteVo;

import java.util.List;

public interface CuisineHistoricalTasteDao {

    int insert(CuisineHistoricalTasteVo record);


    List<CuisineHistoricalTasteVo> selectByCuisineCode(String cuisineCode);

    List<CuisineHistoricalTasteVo> batchElectByCuisineCode(List<String> cuisineCodeList);

    int updateCountByCuisineCodeAndTaste(CuisineHistoricalTasteVo record);
}