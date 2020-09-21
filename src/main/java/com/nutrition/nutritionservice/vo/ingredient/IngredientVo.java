package com.nutrition.nutritionservice.vo.ingredient;

import com.nutrition.nutritionservice.vo.TimeBasedVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 食材Vo。
 * 
 * @author heng.liu
 * @since 2020/9/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class IngredientVo extends TimeBasedVo {

    private String code;

    private String name;

    private String subCategoryCode;

    private String introduction;

}
