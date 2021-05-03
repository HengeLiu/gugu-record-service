package com.nutrition.nutritionservice.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * custom_cuisine_ingredient_rel_tmp
 * @author 
 */
@Data
public class CustomCuisineIngredientRelTmpVo implements Serializable {
    private Integer id;

    /**
     * 自定义菜品编码
     */
    private String customCuisineCode;

    /**
     * 食材编码
     */
    private Integer ingredientCode;

    /**
     * 每份菜品食材重量，克
     */
    private Integer weight;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}