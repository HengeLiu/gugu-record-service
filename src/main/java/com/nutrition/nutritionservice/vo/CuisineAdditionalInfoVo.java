package com.nutrition.nutritionservice.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * cuisine_additional_info
 * @author heng.liu
 */
@Data
public class CuisineAdditionalInfoVo implements Serializable {
    private Integer id;

    /**
     * 菜品编码
     */
    private String cuisineCode;

    /**
     * 价格
     */
    private Double price;

    /**
     * 评分
     */
    private Double ratingScore;

    /**
     * 饿了么外卖小程序地址
     */
    private String eleUrl;

    /**
     * 美团外卖小程序地址
     */
    private String meituanUrl;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}