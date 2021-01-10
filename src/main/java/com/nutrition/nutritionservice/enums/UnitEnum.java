package com.nutrition.nutritionservice.enums;

/**
 * 单位
 *
 * @author heng.liu
 * @since 2021/1/11
 */
public enum UnitEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "未知", "none"),

    MG(1, "毫克", "mg"),

    G(2, "克", "g"),

    PERCENT(3, "百分比", "%"),

    ;

    private final int code;

    private final String desc;

    private final String name;

    UnitEnum(int code, String desc, String name) {
        this.code = code;
        this.desc = desc;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }
}
