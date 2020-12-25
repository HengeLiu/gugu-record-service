package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 食材加工方式枚举。
 * 
 * @author heng.liu
 * @since 2020/12/25
 */
public enum IngredientProcessEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "未知"),

    RAW(1, "生"),

    BLANCH(2, "焯"),

    STIR_FRY(3, "炒"),

    STEAMED(4, "蒸"),

    STEW(5, "煮"),

    PAN_FRY(6, "煎"),

    GRADUAL(6, "炖"),

    MARINATE(6, "腌"),

    ROAST(6, "烤"),

    ;

    private final int code;

    private final String desc;

    IngredientProcessEnum(int code, String desc) {
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
