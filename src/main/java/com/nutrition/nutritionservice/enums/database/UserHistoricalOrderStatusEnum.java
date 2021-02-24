package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 用户历史点餐记录状态枚举
 *
 * @author heng.liu
 * @since 2021/2/24
 */
public enum UserHistoricalOrderStatusEnum implements CodeEnum<Integer> {

    NOT_YET(0, "未记录"),

    YET(1, "已记录"),

    DELETED(2, "已删除"),

    ;

    private final int code;

    private final String desc;

    UserHistoricalOrderStatusEnum(int code, String desc) {
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
