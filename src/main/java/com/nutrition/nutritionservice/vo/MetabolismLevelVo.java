package com.nutrition.nutritionservice.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 摄入水平。
 *
 * @author heng.liu
 * @since 2020/9/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MetabolismLevelVo extends TimeBasedVo {

    private int id;

    private int age;

    private byte gender;

    private double metabolismLevel;

}
