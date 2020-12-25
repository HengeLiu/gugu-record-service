package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 用餐时段枚举。
 * 
 * @author heng.liu
 * @since 2020/12/25
 */
public enum DineTimeEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "未知"),

    BREAKFAST(1, "早餐"),

    LUNCH(2, "午餐"),

    DINNER(3, "晚餐");

    private final int code;

    private final String desc;

    DineTimeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
