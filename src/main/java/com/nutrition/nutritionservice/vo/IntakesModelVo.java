package com.nutrition.nutritionservice.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 摄入模型对象。
 * 
 * @author heng.liu
 * @since 2020/9/13
 */
@Data
@Builder
public class IntakesModelVo {

    private String uuid;

    private List<IngredientCategoryIntakesVo> categoryIntakesList;

}
