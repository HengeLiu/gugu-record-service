package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.user.UserHistoricalCuisineVo;

import java.util.List;

public interface UserHistoricalCuisineDao {

    int countByCuisineCodeAndTaste(String cuisineCode, int taste);

    UserHistoricalCuisineVo selectByUuidAndCuisineCode(String uuid, String cuisineCode);

    UserHistoricalCuisineVo selectLastCuisineByUuid(String uuid);

    int insert(UserHistoricalCuisineVo record);

    List<UserHistoricalCuisineVo> selectByUuid(String uuid);

    int updateStatusByUuidAndCuisineCode(String uuid, String cuisineCode, int status);
}