package com.nutrition.nutritionservice.controller.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 菜品详情
 *
 * @author heng.liu
 * @since 2021/1/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuisineDetailsAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 餐品编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 热量
     */
    private Double calorie;

    /**
     * 在美团中搜索时使用的名称
     */
    private String meituanSearchingName;


    /**
     * 食材列表
     */
    private List<CuisineIngredientAo> ingredientList;

    /**
     * 热量列表
     */
    private List<NutrientWeightAo> nutrientWeightList;

    /**
     * 门店信息
     */
    private StorePreviewAo storeInfo;

}
