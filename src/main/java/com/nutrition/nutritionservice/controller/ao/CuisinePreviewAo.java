package com.nutrition.nutritionservice.controller.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    private String lastAddedDateTimeStr;

    /**
     * 上次添加日期时间
     */
    private String lastAddedDateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 饮食记录id
     */
    private Long cuisineHistoryId;

    /**
     * 是否是点单记录
     */
    private Boolean orderRecord;

    /**
     * 主要食材名称列表字符串
     */
    private String mainIngredientListStr;

    /**
     * 食材列表
     */
    private List<IngredientAo> ingredientList;

    /**
     * 所属门店编码
     */
    private String storeCode;

    /**
     * 所属门店名称
     */
    private String storeName;

}
