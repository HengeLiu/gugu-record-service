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
    private T processedGrains;

    /**
     * 全谷物
     */
    private T unprocessedGrains;

    /**
     * 杂豆
     */
    private T mixedBeans;

    /**
     * 薯类
     */
    private T tuber;

    /**
     * 一般蔬菜
     */
    private T generalVegetables;

    /**
     * 深色蔬菜
     */
    private T darkVegetables;

    /**
     * 水果
     */
    private T fruit;

    /**
     * 禽肉
     */
    private T meat;

    /**
     * 禽肉
     */
    private T poultry;

    /**
     * 水产品
     */
    private T aquatic;

    /**
     * 蛋
     */
    private T egg;

    /**
     * 乳
     */
    private T dairy;

    /**
     * 大豆
     */
    private T soybean;

    /**
     * 坚果
     */
    private T nut;

    /**
     * 烹调油
     */
    private T oil;

    /**
     * 食盐
     */
    private T salt;

}
