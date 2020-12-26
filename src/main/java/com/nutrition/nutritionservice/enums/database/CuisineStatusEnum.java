package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 菜品状态枚举
 * 
 * @author heng.liu
 * @since 2020/12/26
 */
public enum CuisineStatusEnum implements CodeEnum<Integer> {

    SALE(0, "售卖"),

    RUN_OUT(1, "售空"),

    SUSPENDED(2, "暂停"),

    OFF(3, "下架"),

    ;

    private final int code;

    private final String desc;

    CuisineStatusEnum(int code, String desc) {
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
