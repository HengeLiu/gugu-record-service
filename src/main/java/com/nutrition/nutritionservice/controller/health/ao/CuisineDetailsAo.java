package com.nutrition.nutritionservice.controller.health.ao;

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
     * 餐品图片地址
     */
    private String imageUrl;

    /**
     * 食材列表
     */
    private List<CuisineIngredientAo> ingredientList;

    /**
     * 门店信息
     */
    private StorePreviewAo storeInfo;

}
