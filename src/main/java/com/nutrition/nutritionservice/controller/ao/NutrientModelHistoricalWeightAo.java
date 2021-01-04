package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * 营养素模型历史重量
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
public class NutrientModelHistoricalWeightAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 大类总重量
     */
    private Integer supperCategoryWeight;

    /**
     * 大类名称
     */
    private String supperCategoryName;

    /**
     * 大类编码
     */
    private String supperCategoryCode;

    /**
     * 摄入状态
     */
    private Integer intakesStatus;

}
