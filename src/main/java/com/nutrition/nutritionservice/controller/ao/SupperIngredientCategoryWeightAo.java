package com.nutrition.nutritionservice.controller.ao;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户食材分类模型目标值及历史摄入量
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
@Builder
public class SupperIngredientCategoryWeightAo implements Serializable {


    private static final long serialVersionUID = 3265088082256063743L;
    /**
     * 食材大类目标重量
     */
    private Integer supperCategoryTargetWeight;

    /**
     * 食材大类历史摄入重量
     */
    private Integer supperCategoryHistoricalWeight;

    /**
     * 食材大类名称
     */
    private String supperCategoryName;

    /**
     * 食材大类编码
     */
    private Integer supperCategoryCode;

    /**
     * 分类列表
     */
    private List<IngredientCategoryWeightAo> categoryWeightList;

}
