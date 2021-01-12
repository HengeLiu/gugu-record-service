package com.nutrition.nutritionservice.controller.ao;

import com.nutrition.nutritionservice.vo.NutrientWeightVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 用户营养素摄入累计
 *
 * @author heng.liu
 * @since 2021/1/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class NutrientWeightAo extends NutrientWeightVo implements Serializable {
    private static final long serialVersionUID = 6382911904487410846L;

    /**
     * 营养素名称
     */
    private String nutrientName;

    /**
     * 摄入热量
     */
    private Double calorie;

    /**
     * 摄入热量占总热量占比
     */
    private Double percent;

}
