package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.UserStatusInfoVo;

public interface UserStatusInfoDao {

    int insertSelective(UserStatusInfoVo record);

    UserStatusInfoVo selectByUuid(String uuid);

    int updateByUuidSelective(UserStatusInfoVo record);

}