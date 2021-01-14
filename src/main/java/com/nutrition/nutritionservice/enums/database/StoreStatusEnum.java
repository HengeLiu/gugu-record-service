package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 门店状态枚举
 *
 * @author heng.liu
 * @since 2021/1/14
 */
public enum StoreStatusEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "未知"), NOMINATE(1, "提名"), DOCKING(2, "对接中"), ONLINE(3, "已上线"), CLOSED(4, "停业"),

    ;

    private final int code;

    private final String desc;

    StoreStatusEnum(int code, String desc) {
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
