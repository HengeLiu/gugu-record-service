package com.nutrition.nutritionservice.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * user_nutrient_weight_sum_daily
 * 
 * @author heng.liu
 * @since 2021/1/5
 */
@Data
public class UserNutrientWeightSumDailyVo implements Serializable {
    private Integer id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 日期
     */
    private LocalDate date;

    /**
     * 营养素编码
     */
    private Integer nutrientCode;

    /**
     * 今日摄入重量
     */
    private Integer weight;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}