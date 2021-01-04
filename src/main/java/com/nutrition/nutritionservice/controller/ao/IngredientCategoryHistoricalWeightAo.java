package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * 食材分类历史重量
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
public class IngredientCategoryHistoricalWeightAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类别重量
     */
    private Integer categoryWeight;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 类别编码
     */
    private String categoryCode;

    /**
     * 摄入状态，0，不足；1，满足；2，超标
     */
    private Integer intakesStatus;

}
