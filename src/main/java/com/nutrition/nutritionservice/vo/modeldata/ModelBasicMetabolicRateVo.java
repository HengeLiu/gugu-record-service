package com.nutrition.nutritionservice.vo.modeldata;

import lombok.Data;

/**
 * model_basic_metabolic_rate 基础代谢率。
 * 
 * @author heng.liu
 * @since 2020/12/18
 */
@Data
public class ModelBasicMetabolicRateVo {

    private Integer id;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别.1,男;2,女
     */
    private Integer gender;

    /**
     * 基础代谢率(kcal/h/m²)
     */
    private Double bmr;

    private Integer version;

}