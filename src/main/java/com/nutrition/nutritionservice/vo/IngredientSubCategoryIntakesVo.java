package com.nutrition.nutritionservice.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 食物分类摄入对象。
 * 
 * @author heng.liu
 * @since 2020/9/13
 */
@Data
@Builder
public class IngredientSubCategoryIntakesVo {

    private int subCategoryCode;

    private String zhName;

    private int weight;

}
