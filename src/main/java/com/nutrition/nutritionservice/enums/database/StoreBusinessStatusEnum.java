package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 门店营业状态枚举
 *
 * @author heng.liu
 * @since 2021/1/14
 */
public enum StoreBusinessStatusEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "未知"), ON_SALE(1, "营业中"), REST(2, "休息"), SUSPEND_BUSINESS(3, "暂停营业"),;

    private final int code;

    private final String desc;

    StoreBusinessStatusEnum(int code, String desc) {
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
