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

    private String nickName;

    private int gender;

    private int age;

    private int height;

    private int weight;

    private int profeChar;

    private int sportHabits;

}
