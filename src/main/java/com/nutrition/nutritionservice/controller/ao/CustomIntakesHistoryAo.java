package com.nutrition.nutritionservice.controller.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义摄入历史
 *
 * @author heng.liu
 * @since 2021/2/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomIntakesHistoryAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户uuid
     */
    private String uuid;

    /**
     * 餐品编码
     */
    private String cuisineCode;

    /**
     * 食材列表
     */
    private List<CuisineIngredientAo> ingredientList;

}
