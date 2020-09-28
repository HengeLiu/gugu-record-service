package com.nutrition.nutritionservice.common;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 *
 * Response.state
 *
 * @author heng.liu
 * @since 2020/9/28
 */
public enum StateCodeEnum implements CodeEnum<Integer> {
    SUCCESS(0),

    BUSINESS_ERROR(400),

    NO_LOGIN(401),

    NO_PERMISSION(403),

    ILLEGAL_PARAM(405),

    SYSTEM_ERROR(500);

    int code;

    StateCodeEnum(int code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
