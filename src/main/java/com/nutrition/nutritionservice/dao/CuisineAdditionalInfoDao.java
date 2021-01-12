package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.CuisineAdditionalInfoVo;

public interface CuisineAdditionalInfoDao {

    int insert(CuisineAdditionalInfoVo record);

    int insertSelective(CuisineAdditionalInfoVo record);

    CuisineAdditionalInfoVo selectByCuisineCode(String cuisineCode);

    int updateByCuisineCodeSelective(CuisineAdditionalInfoVo record);

}