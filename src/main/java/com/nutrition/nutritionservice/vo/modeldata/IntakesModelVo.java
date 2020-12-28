package com.nutrition.nutritionservice.vo.modeldata;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * model_ingredient_intakes
 * 
 * @author heng.liu
 * @since 2020/12/19
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class IntakesModelVo extends CategoryModel<Integer> {
    private int id;

    /**
     * 日需热量水平
     */
    private int calorie;

    /**
     * 0,未知;1,平衡;2,减脂;3,增肌
     */
    private byte goal;

}