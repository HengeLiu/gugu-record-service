package com.nutrition.nutritionservice.vo;

import lombok.Data;

/**
 * 食物分类摄入对象。
 * 
 * @author heng.liu
 * @since 2020/9/13
 */
@Data
public class FoodIntakesVo {

    private String code;

    private String name;

    private int intakes;

}
