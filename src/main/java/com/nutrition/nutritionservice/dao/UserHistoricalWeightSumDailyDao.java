package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.UserHistoricalWeightSumDailyVo;

public interface UserHistoricalWeightSumDailyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserHistoricalWeightSumDailyVo record);

    int insertSelective(UserHistoricalWeightSumDailyVo record);

    UserHistoricalWeightSumDailyVo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserHistoricalWeightSumDailyVo record);

    int updateByPrimaryKey(UserHistoricalWeightSumDailyVo record);
}