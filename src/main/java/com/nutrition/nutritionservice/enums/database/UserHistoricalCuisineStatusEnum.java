package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 用户历史摄入菜品状态。
 *
 * @author heng.liu
 * @since 2020/12/29
 */
public enum UserHistoricalCuisineStatusEnum implements CodeEnum<Integer> {

    NORMAL(0, "正常"),

    DELETED(1, "已删除"),

    ;

    private final int code;

    private final String desc;

    UserHistoricalCuisineStatusEnum(int code, String desc) {
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
