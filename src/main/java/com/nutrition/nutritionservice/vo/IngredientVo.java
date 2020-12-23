package com.nutrition.nutritionservice.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ingredient
 * @author 
 */
@Data
public class IngredientVo implements Serializable {
    private Integer id;

    /**
     * 食材编码
     */
    private String code;

    /**
     * 食材名称
     */
    private String name;

    /**
     * 每100克热量
     */
    private Integer calorie;

    /**
     * 食材分类码
     */
    private Byte categoryCode;

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

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}