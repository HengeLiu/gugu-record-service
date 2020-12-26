package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 菜品热度枚举。
 *
 * @author heng.liu
 * @since 2020/12/26
 */
public enum CuisineWarmEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "未知"),

    COLD(1, "冷"),

    COOL(2, "凉"),

    WARM(3, "温"),

    HAT(4, "热"),

    ;

    private final int code;

    private final String desc;

    CuisineWarmEnum(int code, String desc) {
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
