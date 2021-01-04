package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 餐品历史记录预览
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
public class CuisineHistoryPreviewAo implements Serializable {
    private static final long serialVersionUID = 1482006365279660245L;

    /**
     * 餐品编码
     */
    private String code;

    /**
     * 餐品名称
     */
    private String name;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 添加日期时间
     */
    private String addedDateTime;

    /**
     * 主要食材名称列表
     */
    private List<String> mainIngredientList;

}
