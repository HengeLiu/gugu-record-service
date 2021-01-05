package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 用户地址类型
 *
 * @author heng.liu
 * @since 2021/1/5
 */
public enum UserLocationTypeEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "未知"),

    HOME(1, "家"),

    COMPANY(2, "公司"),

    CUSTOM(3, "自定义"),

    ;

    private final int code;

    private final String desc;

    UserLocationTypeEnum(int code, String desc) {
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
