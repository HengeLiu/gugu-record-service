package com.nutrition.nutritionservice.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.nutrition.nutritionservice.vo.modeldata.CategoryModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * cuisine_category_weight
 * 
 * @author heng.liu
 * @since 2020/12/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CuisineCategoryWeightVo extends CategoryModel<Integer> implements Serializable {
    private Integer id;

    /**
     * 菜品编码
     */
    private String cuisineCode;

    /**
     * 餐品热量
     */
    private double calorie;

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