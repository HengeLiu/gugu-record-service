package com.nutrition.nutritionservice.enums;

/**
 * 单位
 *
 * @author heng.liu
 * @since 2021/1/11
 */
public enum UnitEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "未知", "none"),

    MILLIGRAMS(1, "毫克", "mg"),

    GRAMS(2, "克", "g"),

    PERCENT(3, "百分比", "%"),

    MICROGRAMS(4, "微克", "μg"),

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
