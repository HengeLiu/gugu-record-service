package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.UserLocationVo;

import java.util.List;

public interface UserLocationDao {
    int deleteById(long id);

    List<UserLocationVo> selectByUuid(String uuid);

    int insertSelective(UserLocationVo record);

    UserLocationVo selectById(long id);

    int updateByIdSelective(UserLocationVo record);

}