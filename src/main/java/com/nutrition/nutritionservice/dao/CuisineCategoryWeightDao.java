package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.CuisineCategoryWeightVo;

import java.util.List;

public interface CuisineCategoryWeightDao {

    List<CuisineCategoryWeightVo> batchSelectByCuisineCodeList(List<String> cuisineCodeList);

    int insert(CuisineCategoryWeightVo record);

    CuisineCategoryWeightVo selectByCuisineCode(String cuisineCode);

    int updateByCuisineCodeSelective(CuisineCategoryWeightVo record);

    int updateByCuisineCode(CuisineCategoryWeightVo record);
}