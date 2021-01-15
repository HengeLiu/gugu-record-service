package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 产品功能状态
 *
 * @author heng.liu
 * @since 2021/1/15
 */
public enum ProductFunctionStatusEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "未知"),

    NOMINATED(1, "提名"),

    PREPARE(2, "准备中"),

    UNDER_DEVELOPMENT(3, "开发中"),

    ON_LINE(4, "已上线"),

    ;

    private final int code;

    private final String desc;

    ProductFunctionStatusEnum(int code, String desc) {
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
