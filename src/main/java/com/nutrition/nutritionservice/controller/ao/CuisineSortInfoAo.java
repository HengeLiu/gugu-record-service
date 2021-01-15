package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜品排序信息
 *
 * @author heng.liu
 * @since 2021/1/8
 */
@Data
public class CuisineSortInfoAo implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 价格
     */
    private Double price;

}
