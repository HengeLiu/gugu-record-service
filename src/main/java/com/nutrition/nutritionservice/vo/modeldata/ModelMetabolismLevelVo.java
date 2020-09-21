package com.nutrition.nutritionservice.vo.modeldata;

import lombok.Data;

/**
 * 摄入水平。
 *
 * @author heng.liu
 * @since 2020/9/13
 */
@Data
public class ModelMetabolismLevelVo {

    private int id;

    private int age;

    private byte gender;

    private double metabolismLevel;

}
