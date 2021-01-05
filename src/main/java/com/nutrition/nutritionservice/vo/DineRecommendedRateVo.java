package com.nutrition.nutritionservice.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.nutrition.nutritionservice.vo.modeldata.CategoryModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * dine_recommended_rate
 * 
 * @author heng.liu
 * @since 2020/12/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DineRecommendedRateVo extends CategoryModel<Double> implements Serializable {
    private Integer id;

    /**
     * 日需热量水平
     */
    private double calorie;

    /**
     * 0,未知;1,平衡;2,减脂;3,增肌
     */
    private byte goal;

    /**
     * 用餐时段
     */
    private byte dineTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}