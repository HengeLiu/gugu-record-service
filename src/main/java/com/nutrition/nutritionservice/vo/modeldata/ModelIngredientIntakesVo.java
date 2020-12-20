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
    private Integer id;

    /**
     * 日需热量水平
     */
    private Integer calorie;

    /**
     * 精制谷物
     */
    private Integer processedGrains;

    /**
     * 全谷物
     */
    private Integer unprocessedGrains;

    /**
     * 杂豆
     */
    private Integer mixedBeans;

    /**
     * 薯类
     */
    private Integer tuber;

    /**
     * 一般蔬菜
     */
    private Integer generalVegetables;

    /**
     * 深色蔬菜
     */
    private Integer darkVegetables;

    /**
     * 水果
     */
    private Integer fruit;

    /**
     * 禽肉
     */
    private Integer meat;

    /**
     * 禽肉
     */
    private Integer poultry;

    /**
     * 水产品
     */
    private Integer aquatic;

    /**
     * 蛋
     */
    private Integer egg;

    /**
     * 乳
     */
    private Integer dairy;

    /**
     * 大豆
     */
    private Integer soybean;

    /**
     * 坚果
     */
    private Integer nut;

    /**
     * 烹调油
     */
    private Integer oil;

    /**
     * 食盐
     */
    private Integer salt;

    /**
     * 0,未知;1,平衡;2,减脂;3,增肌
     */
    private Byte goal;

    /**
     * 版本
     */
    private Integer version;

}