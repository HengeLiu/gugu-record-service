package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * 营养素模型大类目标重量。
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
public class NutrientModelTargetWeightAo implements Serializable {

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

}
