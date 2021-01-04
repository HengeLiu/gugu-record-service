package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * 预加载数据
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
public class PreloadDataAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统用户唯一标识
     */
    private String uuid;

    /**
     * 模型日需千卡热量
     */
    private Integer modelCalorie;

    /**
     * 今日千卡热量摄入历史
     */
    private Integer historicalCalorieDaily;

    /**
     * 用户设置
     */
    private UserSettingsAo userSettings;

    /**
     * 上一次添加的餐品
     */
    private LastAddedCuisineAo lastAddedCuisine;

    /**
     * 用户状态信息
     */
    private UserStatusInfoAo userStatusInfo;

    /**
     * 用户食材分类模型
     */
    private IngredientModelTargetWeightAo userCategoryModel;

    /**
     * 用户食材分类今日摄入历史
     */
    private IngredientModelHistoricalWeightAo historicalCategoryWeightDaily;

    /**
     * 用户营养素模型
     */
    private NutrientModelTargetWeightAo userNutrientModel;

    /**
     * 用户营养素今日摄入历史
     */
    private NutrientModelHistoricalWeightAo historicalNutrientWeightDaily;

}
