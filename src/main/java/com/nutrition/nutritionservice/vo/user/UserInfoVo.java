package com.nutrition.nutritionservice.vo.user;

import com.nutrition.nutritionservice.vo.TimeBasedVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息Vo。
 * 
 * @author heng.liu
 * @since 2020/9/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoVo extends TimeBasedVo {

    private long id;

    private String uuid;

    private int gender;

    private int calorie;

    private int goal;

    private String nickname;

    private int age;

    private int height;

    private int weight;

    private int profeChar;

    private int sportsHabits;

    private int infoStatus;

}
