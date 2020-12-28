package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.CuisineCategoryWightVo;

public interface CuisineCategoryWightDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CuisineCategoryWightVo record);

    int insertSelective(CuisineCategoryWightVo record);

    CuisineCategoryWightVo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CuisineCategoryWightVo record);

    int updateByPrimaryKey(CuisineCategoryWightVo record);
}