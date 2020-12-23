package com.nutrition.nutritionservice.vo.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * user_category_intakes_model
 * 
 * @author heng.liu
 */
@Data
public class UserCategoryIntakesModelVo implements Serializable {
    private int id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 日需热量水平
     */
    private int calorie;

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
     * 0,未知;1,平衡;2,减脂;3,增肌
     */
    private byte goal;

    /**
     * 模型状态.0,使用中;1,已过期.
     */
    private byte modelStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}