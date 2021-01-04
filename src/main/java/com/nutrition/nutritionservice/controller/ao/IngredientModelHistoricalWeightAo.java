package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 食材模型历史重量
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
public class IngredientModelHistoricalWeightAo implements Serializable {

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

    /**
     * 食材类别历史列表
     */
    private List<IngredientCategoryHistoricalWeightAo> categoryHistoricalWeightList;

}
