package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.StoreInfoVo;

import java.util.List;

public interface StoreInfoDao {

    int insert(StoreInfoVo record);

    int insertSelective(StoreInfoVo record);

    StoreInfoVo selectByStoreCode(String storeCode);

    List<StoreInfoVo> selectByStoreCodeList(List<String> storeCodeList);

    List<StoreInfoVo> selectAll();

    List<StoreInfoVo> selectByStatus(int storeStatus, int limit);

}