package com.nutrition.nutritionservice.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * user_historical_weight_sum_daily
 * 
 * @author heng.liu
 * @since 2020/12/28
 */
@Data
public class UserHistoricalWeightSumDailyVo implements Serializable {
    private int id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 日期
     */
    private LocalDate date;

    /**
     * 精制谷物
     */
    private int processedGrains;

    /**
     * 全谷物
     */
    private int unprocessedGrains;

    /**
     * 杂豆
     */
    private int mixedBeans;

    /**
     * 薯类
     */
    private int tuber;

    /**
     * 一般蔬菜
     */
    private int generalVegetables;

    /**
     * 深色蔬菜
     */
    private int darkVegetables;

    /**
     * 水果
     */
    private int fruit;

    /**
     * 禽肉
     */
    private int meat;

    /**
     * 禽肉
     */
    private int poultry;

    /**
     * 水产品
     */
    private int aquatic;

    /**
     * 蛋
     */
    private int egg;

    /**
     * 乳
     */
    private int dairy;

    /**
     * 大豆
     */
    private int soybean;

    /**
     * 坚果
     */
    private int nut;

    /**
     * 烹调油
     */
    private int oil;

    /**
     * 食盐
     */
    private int salt;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}