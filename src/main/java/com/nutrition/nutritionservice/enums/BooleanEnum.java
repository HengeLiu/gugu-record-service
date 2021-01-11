package com.nutrition.nutritionservice.enums;

/**
 * 判断值枚举
 *
 * @author heng.liu
 * @since 2021/1/11
 */
public enum BooleanEnum implements CodeEnum<Integer> {

    FALSE(0, "否"),

    TRUE(1, "是"),

    ;

    private final int code;

    private final String desc;

    BooleanEnum(int code, String desc) {
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
