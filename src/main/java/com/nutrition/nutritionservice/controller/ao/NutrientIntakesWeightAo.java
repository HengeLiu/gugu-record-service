package com.nutrition.nutritionservice.controller.ao;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户营养素摄入累计
 *
 * @author heng.liu
 * @since 2021/1/5
 */
@Data
@Builder
public class NutrientIntakesWeightAo implements Serializable {
    private static final long serialVersionUID = 6382911904487410846L;

    /**
     * 营养素名称
     */
    private String nutrientName;

    /**
     * 营养素编码
     */
    private Integer nutrientCode;

    /**
     * 摄入重量
     */
    private Double weight;

    /**
     * 摄入热量
     */
    private Double calorie;

    /**
     * 摄入热量占总热量占比
     */
    private Double percent;

}
