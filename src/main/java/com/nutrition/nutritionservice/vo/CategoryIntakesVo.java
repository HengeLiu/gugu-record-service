package com.nutrition.nutritionservice.vo;

import lombok.Data;

import java.util.List;

/**
 * 食物大类摄入对象。
 *
 * @author heng.liu
 * @since 2020/9/13
 */
@Data
public class CategoryIntakesVo {

    private String code;

    private String came;

    private int intakes;

    private List<FoodIntakesVo> foodIntakesList;

}
