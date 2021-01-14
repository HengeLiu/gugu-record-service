package com.nutrition.nutritionservice.controller.health.ao;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 食材摄入重量
 *
 * @author heng.liu
 * @since 2021/1/5
 */
@Data
@Builder
public class IngredientCategoryWeightAo implements Serializable {

    private static final long serialVersionUID = 7240322673991946852L;
    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 类别编码
     */
    private Integer categoryCode;

    /**
     * 目标摄入重量
     */
    private Integer targetWeight;

    /**
     * 累计摄入重量
     */
    private Integer historicalWeight;

    /**
     * 常见食材列表字符串
     */
    private String commonIngredientNameListStr;

}
