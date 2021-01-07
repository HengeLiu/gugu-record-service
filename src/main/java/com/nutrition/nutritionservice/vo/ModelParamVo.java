package com.nutrition.nutritionservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 摄入模型用于参数。
 *
 * @author heng.liu
 * @since 2020/9/13
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ModelParamVo {

    /**
     * 性别类型
     */
    private Integer gender;

    /**
     * 模型目标
     */
    private Integer goal;

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

}
