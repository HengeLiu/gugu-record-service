package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 外卖平台枚举
 *
 * @author heng.liu
 * @since 2021/1/5
 */
public enum TakeawayPlatformEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "未知"),

    ELE(1, "饿了么"),

    MEITUAN(2, "美团"),

    ;

    private final int code;

    private final String desc;

    TakeawayPlatformEnum(int code, String desc) {
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
