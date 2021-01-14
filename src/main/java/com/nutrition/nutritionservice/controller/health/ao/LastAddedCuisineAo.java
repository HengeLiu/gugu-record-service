package com.nutrition.nutritionservice.controller.health.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 上一次添加的餐品信息
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LastAddedCuisineAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 餐品编码
     */
    private String cuisineCode;

    /**
     * 添加时间
     */
    private String addedTime;

}
