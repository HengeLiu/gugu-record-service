package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.StoreVo;

import java.util.List;

public interface StoreDao {
    int insert(StoreVo record);

    int insertSelective(StoreVo record);

    StoreVo selectByCode(String code);

    List<StoreVo> selectByCodeList(List<String> codeList);

    List<StoreVo> selectAll();

    //
    // int updateByPrimaryKeySelective(StoreVo record);
    //
    // int updateByPrimaryKey(StoreVo record);
}