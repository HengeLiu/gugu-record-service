package com.nutrition.nutritionservice.vo.nutrient;

import com.nutrition.nutritionservice.vo.TimeBasedVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 营养素Vo。
 * 
 * @author heng.liu
 * @since 2020/9/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NutrientVo extends TimeBasedVo {

    private String code;

    private String name;

    private String categoryCode;

    private String introduction;

}
