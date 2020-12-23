package com.nutrition.nutritionservice.vo.modeldata;

import lombok.Data;

/**
 * model_ingredient_intakes
 * 
 * @author heng.liu
 * @since 2020/12/19
 */
@Data
public class ModelIngredientIntakesVo {
    private int id;

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
     * 版本
     */
    private int version;

}