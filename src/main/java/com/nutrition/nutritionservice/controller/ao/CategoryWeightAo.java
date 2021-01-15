package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * 分类重量
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
public class CategoryWeightAo implements Serializable {

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
    private Integer categoryCode;

}
