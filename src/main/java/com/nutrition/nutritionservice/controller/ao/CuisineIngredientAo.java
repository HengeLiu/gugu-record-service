package com.nutrition.nutritionservice.controller.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 菜品食材信息
 *
 * @author heng.liu
 * @since 2021/1/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuisineIngredientAo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 食材编码
     */
    private Integer code;

    /**
     * 食材名称
     */
    private String name;

    /**
     * 食材重量，克
     */
    private Integer weight;

    /**
     * 是否为主要食材
     */
    private Boolean main;

    /**
     * 食材分类码
     */
    private int categoryCode;

    /**
     * 食材分类名称
     */
    private String categoryName;

}
