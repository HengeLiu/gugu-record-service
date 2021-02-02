package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.store.CuisineVo;

import java.util.List;

public interface CuisineDao {
    int insert(CuisineVo record);

    CuisineVo selectByCode(String code);

    List<CuisineVo> selectByStoreCode(String storeCode);

    List<CuisineVo> selectByDineTime(int dineTime);

    int updateStatusByCode(int code, int status);

    int selectCountByCalorieAndGoal(double minCalorie, double maxCalorie, int goal);

    List<CuisineVo> selectFromIdWithLimit(int id, int limit);

    List<CuisineVo> batchSelect(List<String> cuisineCodeList);

    void updateByCuisineCodeSelective(CuisineVo cuisineVo);

    List<CuisineVo> selectByStoreCodeAndStatus(String storeCode, int status);
}