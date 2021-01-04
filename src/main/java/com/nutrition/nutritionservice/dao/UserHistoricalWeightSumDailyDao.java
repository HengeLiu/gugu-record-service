package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.user.UserHistoricalWeightSumDailyVo;

import java.time.LocalDate;
import java.util.List;

public interface UserHistoricalWeightSumDailyDao {
    int insert(UserHistoricalWeightSumDailyVo record);

    List<UserHistoricalWeightSumDailyVo> selectByUuid(String uuid);

    UserHistoricalWeightSumDailyVo selectByUuidAndDate(String uuid, LocalDate date);

    int updateByUuidAndDate(UserHistoricalWeightSumDailyVo record);
}