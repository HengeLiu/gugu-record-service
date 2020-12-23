package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.store.CuisineVo;

public interface CuisineDao {
    int insert(CuisineVo record);

    CuisineVo selectByCode(String code);

    int updateStatusByCode(int code, int status);

    int selectCountByCalorieAndGoal(int minCalorie, int maxCalorie, int goal);

}