package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 用户食材模型状态枚举
 *
 * @author heng.liu
 * @since 2021/1/6
 */
public enum UserIngredientModelStatusEnum implements CodeEnum<Integer> {

    USING(0, "使用中"),

    DEPRECATED(1, "已过期"),

    ;

    private final int code;

    private final String desc;

    UserIngredientModelStatusEnum(int code, String desc) {
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
