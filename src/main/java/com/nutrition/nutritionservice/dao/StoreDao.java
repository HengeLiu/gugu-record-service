package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.StoreVo;

public interface StoreDao {
    int insert(StoreVo record);

    int insertSelective(StoreVo record);

    StoreVo selectByCode(String code);
    //
    // int updateByPrimaryKeySelective(StoreVo record);
    //
    // int updateByPrimaryKey(StoreVo record);
}