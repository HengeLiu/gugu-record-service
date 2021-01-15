package com.nutrition.nutritionservice.controller.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author heng.liu
 * @since 2021/1/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreRecommendAo implements Serializable {
    private static final long serialVersionUID = -5993725254329286919L;

    private String uuid;

    private String storeName;

    private String userAddress;

}
