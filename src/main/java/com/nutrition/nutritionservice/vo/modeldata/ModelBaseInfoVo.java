package com.nutrition.nutritionservice.vo.modeldata;

import lombok.Data;

import java.io.Serializable;

/**
 * 模型基础信息。
 *
 * @author heng.liu
 * @since 2020/12/18
 */
@Data
public class ModelBaseInfoVo implements Serializable {

    private static final long serialVersionUID = -3758732890287844700L;

    private int gender;

    private int age;

    private int height;

    private int weight;

    private int profeChar;

    private int sportHabits;

}
