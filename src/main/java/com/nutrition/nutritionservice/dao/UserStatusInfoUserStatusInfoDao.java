package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.UserStatusInfoVo;

public interface UserStatusInfoUserStatusInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(UserStatusInfoVo record);

    int insertSelective(UserStatusInfoVo record);

    UserStatusInfoVo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserStatusInfoVo record);

    int updateByPrimaryKey(UserStatusInfoVo record);
}