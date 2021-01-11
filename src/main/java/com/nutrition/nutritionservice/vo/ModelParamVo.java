package com.nutrition.nutritionservice.vo;

import lombok.AllArgsConstructor;
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
    private Integer sportsHabits;

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

}
