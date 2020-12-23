package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 性别枚举。
 *
 * @author heng.liu
 * @since 2020/12/20
 */
public enum GenderEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "未知"),

    FEMALE(1, "女"),

    MALE(2, "男");

    private final int code;

    private final String desc;

    GenderEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
