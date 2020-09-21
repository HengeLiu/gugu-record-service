package com.nutrition.nutritionservice.vo.user;

import com.nutrition.nutritionservice.vo.TimeBasedVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户历史摄入的食材。
 * 
 * @author heng.liu
 * @since 2020/9/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserHistoricalIngredientVo extends TimeBasedVo {

    private long id;

    private String uuid;

    private String cuisineCode;

    private int ingredient_weight;

}
