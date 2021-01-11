package com.nutrition.nutritionservice.converter;

import com.nutrition.nutritionservice.controller.ao.UserInfoAo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;

/**
 * @author heng.liu
 * @since 2021/1/11
 */
public class UserInfoAo2VoConverter {

    public static UserInfoVo convert(UserInfoAo userInfoAo, double calorie, long activeModelId) {
        return UserInfoVo.builder().uuid(userInfoAo.getUuid()).activeModelId(activeModelId).age(userInfoAo.getAge())
                .calorie(calorie).gender(userInfoAo.getGender()).goal(userInfoAo.getGoal())
                .height(userInfoAo.getHeight()).weight(userInfoAo.getWeight()).profeChar(userInfoAo.getProfeChar())
                .sportsHabits(userInfoAo.getSportsHabits()).build();
    }

}
