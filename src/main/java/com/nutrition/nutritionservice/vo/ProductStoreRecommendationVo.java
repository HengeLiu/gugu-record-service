package com.nutrition.nutritionservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * product_store_recommendation
 * @author heng.liu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStoreRecommendationVo implements Serializable {
    private Integer id;

    /**
     * 用户uuid
     */
    private String uuid;

    /**
     * 店名
     */
    private String storeName;

    /**
     * 用户地址
     */
    private String userAddress;

    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}