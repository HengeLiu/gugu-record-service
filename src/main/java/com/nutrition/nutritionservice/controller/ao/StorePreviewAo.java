package com.nutrition.nutritionservice.controller.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 门店预览
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorePreviewAo implements Serializable {
    private static final long serialVersionUID = 2428805046031545347L;
    /**
     * 门店编码
     */
    private String code;

    /**
     * 门店名称
     */
    private String name;

    /**
     * 美团外卖小程序地址
     */
    private String meituanMiniAppPath;

    /**
     * 饿了么小程序地址
     */
    private String eleMiniAppPath;

    /**
     * 地址名称
     */
    private String addressTitle;

    /**
     * 门店与当前查询位置距离，米
     */
    private Double distance;

    /**
     * 平均配送时间，分钟
     */
    private Double deliveryTime;

    /**
     * 总评分
     */
    private Double ratingScore;

    /**
     * 月销量
     */
    private Integer monthlySales;

}
