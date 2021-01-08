package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * 门店预览
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
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

    /**
     * 图片地址
     */
    private String imageUrl;

}