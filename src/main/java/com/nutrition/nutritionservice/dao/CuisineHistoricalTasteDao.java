package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.CuisineHistoricalTasteVo;

public interface CuisineHistoricalTasteDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CuisineHistoricalTasteVo record);

    int insertSelective(CuisineHistoricalTasteVo record);

    CuisineHistoricalTasteVo selectByCuisineCode(Integer id);

    int updateByCuisineCodeAndTasteSelective(CuisineHistoricalTasteVo record);

    int updateByCuisineCodeAndTaste(CuisineHistoricalTasteVo record);
}