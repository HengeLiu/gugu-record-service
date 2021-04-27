package com.nutrition.nutritionservice.vo.modeldata;

import lombok.Data;

/**
 * 模型属性父类
 *
 * @author heng.liu
 * @since 2020/12/28
 */
@Data
public class CategoryModel {

    /**
     * 精制谷物
     */
    protected Integer processedGrains;

    /**
     * 全谷物
     */
    protected Integer unprocessedGrains;

    /**
     * 杂豆
     */
    protected Integer mixedBeans;

    /**
     * 薯类
     */
    protected Integer tuber;

    /**
     * 一般蔬菜
     */
    protected Integer generalVegetables;

    /**
     * 深色蔬菜
     */
    protected Integer darkVegetables;

    /**
     * 水果
     */
    protected Integer fruit;

    /**
     * 禽肉
     */
    protected Integer meat;

    /**
     * 禽肉
     */
    protected Integer poultry;

    /**
     * 水产品
     */
    protected Integer aquatic;

    /**
     * 蛋
     */
    protected Integer egg;

    /**
     * 乳
     */
    protected Integer dairy;

    /**
     * 大豆
     */
    protected Integer soybean;

    /**
     * 坚果
     */
    protected Integer nut;

    /**
     * 烹调油
     */
    protected Integer oil;

    /**
     * 食盐
     */
    protected Integer salt;

}
