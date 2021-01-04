package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 食材分类目标重量
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
public class IngredientCategoryTargetWeightAo implements Serializable {

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
     * 常见食材列表
     */
    private List<String> commonIngredientNameList;


}
