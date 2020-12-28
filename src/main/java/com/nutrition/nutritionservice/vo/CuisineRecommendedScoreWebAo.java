package com.nutrition.nutritionservice.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜品推荐分数传输对象。
 *
 * @author heng.liu
 * @since 2020/12/28
 */
@Data
public class CuisineRecommendedScoreWebAo implements Serializable {

    private static final long serialVersionUID = 8807420157667227578L;
    
    private String cuisineCode;

    /**
     * 综合推荐分数。
     */
    private double recommendedScore;

    /**
     * 模型推荐分数。
     */
    private double modelScore;

    /**
     * 美味推荐分数。
     */
    private double tasteScore;

    /**
     * 时段推荐分数。
     */
    private double dineScore;

    /**
     * 偏好推荐分数。
     */
    private double preferenceScore;

}
