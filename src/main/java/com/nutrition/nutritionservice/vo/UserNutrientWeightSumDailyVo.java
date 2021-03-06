package com.nutrition.nutritionservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * user_nutrient_weight_sum_daily
 * 
 * @author heng.liu
 * @since 2021/1/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserNutrientWeightSumDailyVo extends NutrientWeightVo implements Serializable {
    private Integer id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 日期
     */
    private LocalDate date;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}