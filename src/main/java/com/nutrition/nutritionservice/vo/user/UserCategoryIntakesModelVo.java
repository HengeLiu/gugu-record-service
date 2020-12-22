package com.nutrition.nutritionservice.vo.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * user_category_intakes_model
 * 
 * @author
 */
@Data
public class UserCategoryIntakesModelVo implements Serializable {
    private Integer id;

    /**
     * 用户唯一标识
     */
    private String uuid;

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
     * 创建时间
     */
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}