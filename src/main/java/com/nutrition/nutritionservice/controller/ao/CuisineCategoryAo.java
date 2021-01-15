package com.nutrition.nutritionservice.controller.ao;

import com.nutrition.nutritionservice.controller.ao.CuisinePreviewAo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 餐品分类
 *
 * @author heng.liu
 * @since 2021/1/8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuisineCategoryAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类编码
     */
    private Integer categoryCode;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 餐品列表
     */
    private List<CuisinePreviewAo> cuisineList;

}
