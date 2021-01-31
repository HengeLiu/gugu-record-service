package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.user.UserHistoricalCuisineVo;

import java.time.LocalDateTime;
import java.util.List;

public interface UserHistoricalCuisineDao {

    int countByCuisineCodeAndTaste(String cuisineCode, int taste);

    UserHistoricalCuisineVo selectByUuidAndCuisineCode(String uuid, String cuisineCode);

    UserHistoricalCuisineVo selectLastCuisineByUuid(String uuid);

    int insert(UserHistoricalCuisineVo record);

    List<UserHistoricalCuisineVo> selectByUuid(String uuid);

    List<UserHistoricalCuisineVo> selectByUuidAndDateTime(String uuid, LocalDateTime startTime, LocalDateTime endTime);

    void updateStatusById(long userHistoricalCuisineId, int status);

    UserHistoricalCuisineVo selectById(long userHistoricalCuisineId);

    List<UserHistoricalCuisineVo> selectLastByUuidLimit(String uuid, int limit);
}