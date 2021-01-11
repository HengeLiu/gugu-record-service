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
     * 价格
     */
    private Double price;

    /**
     * 推荐总分
     */
    private Double totalScore;

    /**
     * 评分
     */
    private Double ratingScore;

    /**
     * 模型匹配度
     */
    private Double modelMatchingScore;

    /**
     * 餐品图片地址
     */
    private String imageUrl;

    /**
     * 食材列表
     */
    private List<CuisineIngredientAo> ingredientList;

    /**
     * 外卖平台地址
     */
    private List<CuisineTakeawayPlatformUrlAo> takeawayPlatformUrlList;

    /**
     * 菜品食材种类统计
     */
    private List<SupperIngredientCategoryWeightAo> ingredientCategoryList;

    /**
     * 营养素重量
     */
    private List<NutrientWeightAo> nutrientWeightList;

    /**
     * 门店信息
     */
    private StorePreviewAo storeInfo;

}
