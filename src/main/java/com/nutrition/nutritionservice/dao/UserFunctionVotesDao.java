package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.UserFunctionVotesVo;

import java.time.LocalDateTime;
import java.util.List;

public interface UserFunctionVotesDao {
    int insert(UserFunctionVotesVo record);

    List<UserFunctionVotesVo> selectByUuidAndDateTime(String uuid, LocalDateTime startTime, LocalDateTime endTime);
}