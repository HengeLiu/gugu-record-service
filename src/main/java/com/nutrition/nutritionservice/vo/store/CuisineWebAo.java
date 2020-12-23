package com.nutrition.nutritionservice.vo.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author heng.liu
 * @since 2020/12/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuisineWebAo implements Serializable {

    private static final long serialVersionUID = 2127886022694767504L;
    /**
     * 菜品名称
     */
    private String name;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 菜品热量
     */
    private Integer calorie;

    /**
     * 推荐目标
     */
    private Byte goal;

    /**
     * 菜品种类
     */
    private Byte cuisineType;

    /**
     * 用餐时段
     */
    private Byte dineTime;

    /**
     * 菜温
     */
    private Byte warm;

    /**
     * 精制谷物
     */
    private String processedGrains;

    /**
     * 全谷物
     */
    private String unprocessedGrains;

    /**
     * 杂豆
     */
    private String mixedBeans;

    /**
     * 薯类
     */
    private String tuber;

    /**
     * 一般蔬菜
     */
    private String generalVegetables;

    /**
     * 深色蔬菜
     */
    private String darkVegetables;

    /**
     * 水果
     */
    private String fruit;

    /**
     * 畜肉
     */
    private String meat;

    /**
     * 禽肉
     */
    private String poultry;

    /**
     * 水产品
     */
    private String aquatic;

    /**
     * 蛋
     */
    private String egg;

    /**
     * 乳
     */
    private String dairy;

    /**
     * 大豆
     */
    private String soybean;

    /**
     * 坚果
     */
    private String nut;

    /**
     * 烹调油
     */
    private String oil;

    /**
     * 食盐
     */
    private String salt;

    /**
     * 糖
     */
    private String sugar;

}
