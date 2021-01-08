package com.nutrition.nutritionservice.vo.store;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

/**
 * cuisine_ingredient_rel
 * 
 * @author heng.liu
 * @since 2020/12/25
 */
@Data
@Builder
public class CuisineIngredientRelVo implements Serializable {
    private int id;

    /**
     * 菜品编码
     */
    private String cuisineCode;

    /**
     * 食材编码
     */
    private int ingredientCode;

    /**
     * 每份菜品食材重量
     */
    private int weight;

    /**
     * 加工方式
     */
    private int process;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}