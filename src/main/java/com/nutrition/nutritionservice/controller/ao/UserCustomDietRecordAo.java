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
public class UserCustomDietRecordAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户uuid
     */
    private String uuid;

    /**
     * 用户历史餐品记录id
     * @apiNote 更新时需要此字段
     */
    private Long userHistoricalCuisineId;

    /**
     * 餐品编码
     */
    private String cuisineCode;

    /**
     * 食材列表
     */
    private List<CuisineIngredientAo> ingredientList;

}
