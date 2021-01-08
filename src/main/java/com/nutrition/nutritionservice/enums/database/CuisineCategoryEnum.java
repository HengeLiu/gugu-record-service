package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 菜品类型枚举
 *
 * @author heng.liu
 * @since 2020/12/26
 */
public enum CuisineCategoryEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "未知"),

    SET(1, "套餐"),

    ;

    private final int code;

    private final String name;

    CuisineCategoryEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
