package com.nutrition.nutritionservice.controller.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
     * 产品图片地址
     */
    private String imageUrl;

    /**
     * 上次添加日期时间
     */
    private String lastAddedDateTime;

    /**
     * 主要食材名称列表
     */
    private List<String> mainIngredientList;

    /**
     * 所属门店编码
     */
    private String storeCode;

    /**
     * 所属门店名称
     */
    private String storeName;

}
