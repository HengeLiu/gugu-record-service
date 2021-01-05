package com.nutrition.nutritionservice.vo.user;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.nutrition.nutritionservice.vo.modeldata.CategoryModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * user_category_intakes_model
 * 
 * @author heng.liu
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserCategoryIntakesModelVo extends CategoryModel<Integer> implements Serializable {
    private int id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 日需热量水平
     */
    private double calorie;

    /**
     * 0,未知;1,平衡;2,减脂;3,增肌
     */
    private byte goal;

    /**
     * 模型状态.0,使用中;1,已过期.
     */
    private byte modelStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}