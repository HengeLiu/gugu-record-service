package com.nutrition.nutritionservice.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * dine_recommended_rate
 * 
 * @author heng.liu
 * @since 2020/12/25
 */
@Data
public class DineRecommendedRateVo implements Serializable {
    private Integer id;

    /**
     * 日需热量水平
     */
    private int calorie;

    /**
     * 0,未知;1,平衡;2,减脂;3,增肌
     */
    private byte goal;

    /**
     * 用餐时段
     */
    private byte dineTime;

    /**
     * 精制谷物比例
     */
    private double processedGrains;

    /**
     * 全谷物比例
     */
    private double unprocessedGrains;

    /**
     * 杂豆比例
     */
    private double mixedBeans;

    /**
     * 薯类比例
     */
    private double tuber;

    /**
     * 一般蔬菜比例
     */
    private double generalVegetables;

    /**
     * 深色蔬菜比例
     */
    private double darkVegetables;

    /**
     * 水果比例
     */
    private double fruit;

    /**
     * 禽肉比例
     */
    private double meat;

    /**
     * 禽肉比例
     */
    private double poultry;

    /**
     * 水产品比例
     */
    private double aquatic;

    /**
     * 蛋比例
     */
    private double egg;

    /**
     * 乳比例
     */
    private double dairy;

    /**
     * 大豆比例
     */
    private double soybean;

    /**
     * 坚果比例
     */
    private double nut;

    /**
     * 烹调油比例
     */
    private double oil;

    /**
     * 食盐比例
     */
    private double salt;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}