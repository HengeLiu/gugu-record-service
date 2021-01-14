package com.nutrition.nutritionservice.controller.ao;

import com.nutrition.nutritionservice.controller.health.ao.NutrientWeightAo;
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
     * 门店图标地址列表
     */
    private List<String> storeIconUrlList;

    /**
     * 今日已催更次数
     */
    private Integer todayPushingTime;

    /**
     * 营养素摄入累计
     */
    private List<NutrientWeightAo> userNutrientHistoricalIntakesDaily;

}
