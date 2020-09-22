package com.nutrition.nutritionservice.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 摄入模型用于参数。
 *
 * @author heng.liu
 * @since 2020/9/13
 */
@Data
@Builder
public class IntakesModelUserInfoParamVo {

    private int gender;

    private int stature;

    private int weight;

    private int age;

    private int sportLevel;

}
