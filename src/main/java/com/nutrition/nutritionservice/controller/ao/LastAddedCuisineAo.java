package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * 上一次添加的餐品信息
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
public class LastAddedCuisineAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 餐品编码
     */
    private String cuisineCode;

    /**
     * 餐品名称
     */
    private String cuisineName;

    /**
     * 添加时间
     */
    private String addedTime;

}
