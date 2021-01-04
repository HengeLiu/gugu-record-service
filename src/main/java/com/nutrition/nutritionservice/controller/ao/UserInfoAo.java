package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
public class UserInfoAo implements Serializable {
    private static final long serialVersionUID = 2791927152656988438L;

    /**
     * 系统用户唯一标识
     */
    private String uuid;

    /**
     * 性别，1：女，2：男
     */
    private Integer gender;

    /**
     * 目标，1：平衡，2：减脂，3：增肌
     */
    private Integer goal;

    /**
     * 职业活动水平，1：久坐，2：坐站均衡，3：久站，4：重体力劳动
     */
    private Integer profeChar;

    /**
     * 运动习惯，1：从不运动，2：偶尔活动身体，3：偶尔运动，4：经常运动
     */
    private Integer sportHabit;

    /**
     * 年龄，周岁
     */
    private Integer age;

    /**
     * 身高，厘米
     */
    private Integer height;

    /**
     * 体重，千克
     */
    private Integer weight;

    /**
     * 是否点击了“跳过”
     */
    private boolean skip;

}
