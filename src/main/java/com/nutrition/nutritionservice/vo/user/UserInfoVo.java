package com.nutrition.nutritionservice.vo.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user_info
 * 
 * @author heng.liu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo implements Serializable {
    private long id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 性别类型
     */
    private Integer gender;

    /**
     * 日需热量
     */
    private Double calorie;

    /**
     * 模型目标
     */
    private Integer goal;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 身高（厘米）
     */
    private Integer height;

    /**
     * 体重（千克）
     */
    private Integer weight;

    /**
     * 职业活动.0,久坐;1,坐站均匀;2,久站;3,重体力劳动
     */
    private Integer profeChar;

    /**
     * 运动习惯.0,无运动习惯;1,偶尔活动身体;2,偶尔运动;3,经常运动
     */
    private Integer sportsHabits;

    /**
     * 当前使用的用户模型
     */
    private Long activeModelId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}