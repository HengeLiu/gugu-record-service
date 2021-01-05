package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 宏观营养素枚举
 *
 * @author heng.liu
 * @since 2021/1/4
 */
public enum MacronutrientEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "其他"),

    PROTEIN(1, "蛋白质"),

    CARBOHYDRATE(2, "碳水化合物"),

    LIPIDS(3, "脂质"),

    VITAMIN(4, "维生素"),

    MINERAL(5, "矿物质"),

    ;

    private final int code;

    private final String desc;

    MacronutrientEnum(int code, String desc) {
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
