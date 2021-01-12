package com.nutrition.nutritionservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 营养素重量
 *
 * @author heng.liu
 * @since 2021/1/12
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class NutrientWeightVo {

    /**
     * 营养素编码
     */
    private Integer nutrientCode;

    /**
     * 重量，克
     */
    private Double weight;

}
