package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.UserSettingVo;

public interface UserSettingDao {

    int insertSelective(UserSettingVo record);

    UserSettingVo selectByUuid(String uuid);

    int updateByUuidSelective(UserSettingVo record);

}