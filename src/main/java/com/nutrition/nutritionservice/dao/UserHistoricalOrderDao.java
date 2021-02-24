package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.UserHistoricalOrderVo;

import java.util.List;

public interface UserHistoricalOrderDao {

    UserHistoricalOrderVo selectById(long id);

    UserHistoricalOrderVo selectLastByUuid(String uuid);

    List<UserHistoricalOrderVo> selectByUuid(String uuid, int limit);

    int insert(String uuid, String cuisineCode);

    int updateStatusById(long id, int status);
}