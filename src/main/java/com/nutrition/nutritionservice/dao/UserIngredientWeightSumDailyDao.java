package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.user.UserIngredientWeightSumDailyVo;

import java.time.LocalDate;
import java.util.List;

public interface UserIngredientWeightSumDailyDao {
    int insert(UserIngredientWeightSumDailyVo record);

    List<UserIngredientWeightSumDailyVo> selectByUuid(String uuid);

    UserIngredientWeightSumDailyVo selectByUuidAndDate(String uuid, LocalDate date);

    int updateByUuidAndDate(UserIngredientWeightSumDailyVo record);
}