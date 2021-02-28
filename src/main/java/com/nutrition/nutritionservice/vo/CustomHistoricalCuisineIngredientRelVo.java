package com.nutrition.nutritionservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * custom_historical_cuisine_ingredient_rel
 * 
 * @author heng.liu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomHistoricalCuisineIngredientRelVo implements Serializable {
    private Long id;

    /**
     * 用户餐品记录id
     */
    private Long userHistoricalCuisineId;

    /**
     * 用户uuid
     */
    private String uuid;

    /**
     * 食材编码
     */
    private Integer ingredientCode;

    /**
     * 食材重量，克
     */
    private Integer weight;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}