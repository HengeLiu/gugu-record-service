package com.nutrition.nutritionservice.controller.health.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 菜品食材信息
 *
 * @author heng.liu
 * @since 2021/1/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuisineIngredientAo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 食材编码
     */
    private Integer code;

    /**
     * 食材名称
     */
    private String name;

    /**
     * 食材重量，克
     */
    private Integer weight;

    /**
     * 食材图片地址
     */
    private String imageUrl;

}
