package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜品食材信息
 *
 * @author heng.liu
 * @since 2021/1/11
 */
@Data
public class CuisineIngredientAo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 食材编码
     */
    private String code;

    /**
     * 食材名称
     */
    private String name;

    /**
     * 食材重量，克
     */
    private Integer weight;

    /**
     * 食材图片地址
     */
    private String imageUrl;

}
