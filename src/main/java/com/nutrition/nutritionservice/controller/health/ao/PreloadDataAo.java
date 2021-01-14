package com.nutrition.nutritionservice.controller.health.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 预加载数据
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreloadDataAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统用户唯一标识
     */
    private String uuid;

    /**
     * 模型日需千卡热量
     */
    private Double modelCalorie;

    /**
     * 今日千卡热量摄入历史
     */
    private Double historicalCalorieDaily;

    /**
     * 用户设置
     */
    private UserSettingsAo userSettings;

    /**
     * 系统默认点餐位置
     */
    private LocationAo systemDefaultOrderLocation;

    /**
     * 用户状态信息
     */
    private UserStatusInfoAo userStatusInfo;

    /**
     * 上一次添加的餐品
     */
    private LastAddedCuisineAo lastAddedCuisine;

    /**
     * 用户食材分类模型目标值及历史摄入量
     */
    private List<SupperIngredientCategoryWeightAo> ingredientCategoryWeightList;

    /**
     * 营养素摄入累计
     */
    private List<NutrientWeightAo> userNutrientHistoricalIntakesDaily;

}
