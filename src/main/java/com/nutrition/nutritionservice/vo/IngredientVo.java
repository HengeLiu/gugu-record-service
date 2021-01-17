package com.nutrition.nutritionservice.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ingredient
 * 
 * @author heng.liu
 * @since 2020/12/28
 */
@Data
public class IngredientVo implements Serializable {
    private int id;

    /**
     * 食材编码
     */
    private int code;

    /**
     * 食材名称
     */
    private String name;

    /**
     * 每100克热量
     */
    private double calorie;

    /**
     * 食材分类码
     */
    private int categoryCode;

    /**
     * 食材分类名称
     */
    private String categoryName;

    /**
     * 别名
     */
    private String nicknames;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 食材类型
     */
    private String ingredientType;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}