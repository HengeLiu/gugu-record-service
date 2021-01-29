package com.nutrition.nutritionservice.controller.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 餐品预览
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
     * 热量
     */
    private Double calorie;

    /**
     * 排序优先级
     */
    private Integer sortPriority;

    /**
     * 上次添加日期时间
     */
    private String lastAddedDateTime;

    /**
     * 记录id
     */
    private Long cuisineHistoryId;

    /**
     * 主要食材名称列表字符串
     */
    private String mainIngredientListStr;

    /**
     * 所属门店编码
     */
    private String storeCode;

    /**
     * 所属门店名称
     */
    private String storeName;

}
