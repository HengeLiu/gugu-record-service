package com.nutrition.nutritionservice.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ingredient_nutrient_rel
 * 
 * @author heng.liu
 */
@Data
public class IngredientNutrientRelVo implements Serializable {
    private Integer id;

    /**
     * 食材编码
     */
    private Integer ingredientCode;

    /**
     * 营养素编码，{@link com.nutrition.nutritionservice.enums.database.NutrientEnum}
     */
    private Integer nutrientCode;

    /**
     * 每100克食材营养素含量
     */
    private String nutrientContent;

    private String contentUnit;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}