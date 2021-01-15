package com.nutrition.nutritionservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * user_recommendation
 * 
 * @author heng.liu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUserRecommendationVo implements Serializable {
    private Integer id;

    /**
     * 用户uuid
     */
    private String uuid;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 用户输入内容
     */
    private String content;

    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}