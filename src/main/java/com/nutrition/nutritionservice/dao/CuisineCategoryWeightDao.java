package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.CuisineCategoryWeightVo;

import java.util.List;

public interface CuisineCategoryWeightDao {

    List<CuisineCategoryWeightVo> batchSelectByCuisineCodeList(List<String> cuisineCodeList);

    int insert(CuisineCategoryWeightVo record);

    int insertSelective(CuisineCategoryWeightVo record);

    CuisineCategoryWeightVo selectByCuisineCode(Integer id);

    int updateByCuisineCodeSelective(CuisineCategoryWeightVo record);

    int updateByCuisineCode(CuisineCategoryWeightVo record);
}