package com.nutrition.nutritionservice.vo.user;

import com.nutrition.nutritionservice.vo.modeldata.CategoryModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * user_ingredient_model
 * 
 * @author heng.liu
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserIngredientCategoryModelVo extends CategoryModel implements Serializable {
    private long id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 标准摄入热量
     */
    private double calorie;

    /**
     * 0,未知;1,平衡;2,减脂;3,增肌
     */
    private int goal;

    /**
     * 模型状态.0,使用中;1,已过期.
     */
    private int modelStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}