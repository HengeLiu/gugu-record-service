package com.nutrition.nutritionservice.vo.modeldata;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * model_ingredient_category_model
 * 
 * @author heng.liu
 * @since 2020/12/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ModelIngredientCategoryModelVo extends CategoryModel {

    private int id;

    /**
     * 标准摄入热量
     */
    private double standardCalorie;

    /**
     * 目标摄入热量
     */
    private double targetCalorie;

    /**
     * 0,未知;1,平衡;2,减脂;3,增肌
     */
    private int goal;

}