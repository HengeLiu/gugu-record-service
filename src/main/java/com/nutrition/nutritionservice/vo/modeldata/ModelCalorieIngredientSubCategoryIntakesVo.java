package com.nutrition.nutritionservice.vo.modeldata;

import lombok.Data;

/**
 * 日需热量下的食材摄入量。
 *
 * @author heng.liu
 * @since 2020/9/21
 */
@Data
public class ModelCalorieIngredientSubCategoryIntakesVo {

    private double calorie;

    private int ingredientSubCategoryCode;

    private String ingredientSubCategory;
    
    private double dailyWeight;

}
