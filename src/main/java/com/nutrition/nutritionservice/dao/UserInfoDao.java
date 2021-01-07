package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.user.UserInfoVo;

import java.util.List;

public interface UserInfoDao {

    int insert(UserInfoVo record);

    UserInfoVo selectByUuid(String uuid);

    UserInfoVo selectById(Long id);

    int updateByUuidSelective(UserInfoVo record);

    List<UserInfoVo> selectAll();

}