package com.nutrition.nutritionservice.vo.modeldata;

import lombok.Data;

/**
 * 模型属性父类
 *
 * @author heng.liu
 * @since 2020/12/28
 */
@Data
public class CategoryModel<T> {

    /**
     * 精制谷物
     */
    protected T processedGrains;

    /**
     * 全谷物
     */
    protected T unprocessedGrains;

    /**
     * 杂豆
     */
    protected T mixedBeans;

    /**
     * 薯类
     */
    protected T tuber;

    /**
     * 一般蔬菜
     */
    protected T generalVegetables;

    /**
     * 深色蔬菜
     */
    protected T darkVegetables;

    /**
     * 水果
     */
    protected T fruit;

    /**
     * 禽肉
     */
    protected T meat;

    /**
     * 禽肉
     */
    protected T poultry;

    /**
     * 水产品
     */
    protected T aquatic;

    /**
     * 蛋
     */
    protected T egg;

    /**
     * 乳
     */
    protected T dairy;

    /**
     * 大豆
     */
    protected T soybean;

    /**
     * 坚果
     */
    protected T nut;

    /**
     * 烹调油
     */
    protected T oil;

    /**
     * 食盐
     */
    protected T salt;

}
