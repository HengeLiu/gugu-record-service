package com.nutrition.nutritionservice.controller.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 食材
 *
 * @author heng.liu
 * @since 2021/1/26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 食材编码
     */
    private int code;

    /**
     * 食材名称
     */
    private String name;

    /**
     * 名称拼音首字母
     */
    private Character firstPinyin;

    /**
     * 每100克热量
     */
    private double calorie;

    /**
     * 食材分类码
     */
    private int categoryCode;

    /**
     * 食材分类名称
     */
    private String categoryName;

    /**
     * 别名
     */
    private String nicknames;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 食材类型
     */
    private String ingredientType;

}
