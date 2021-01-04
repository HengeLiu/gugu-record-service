package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * 餐品预览
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
public class CuisinePreviewAo implements Serializable {

    private static final long serialVersionUID = -7579564701414196237L;

    /**
     * 餐品编码
     */
    private String code;

    /**
     * 餐品名称
     */
    private String name;

    /**
     * 价格
     */
    private Double price;

    /**
     * 总分
     */
    private Double totalScore;

    /**
     * 模型匹配度
     */
    private Double modelMatchingScore;

    /**
     * 评价分数
     */
    private Double ratingScore;

    /**
     * 月销量
     */
    private Integer monthlySales;

    /**
     * 产品图片地址
     */
    private String imageUrl;

    /**
     * 所属门店编码
     */
    private String storeCode;

    /**
     * 所属门店名称
     */
    private String storeName;

    /**
     * 所属门店与当前查询位置距离，米
     */
    private Double storeDistance;

    /**
     * 所属平均配送时间，分钟
     */
    private Double storeDeliveryTime;

}
