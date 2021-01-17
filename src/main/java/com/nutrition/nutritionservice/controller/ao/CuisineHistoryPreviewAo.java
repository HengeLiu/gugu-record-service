package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

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
    
}
