package com.nutrition.nutritionservice.controller.health.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 门店餐品列表
 *
 * @author heng.liu
 * @since 2021/1/8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreCuisineListAo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String storeCode;

    private String storeName;

    /**
     * 餐品分类列表
     */
    private List<CuisineCategoryAo> cuisineCategoryList;

}
