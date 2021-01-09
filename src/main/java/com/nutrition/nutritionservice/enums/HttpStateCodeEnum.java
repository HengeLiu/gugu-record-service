package com.nutrition.nutritionservice.enums;

/**
 *
 * Response.state
 *
 * @author heng.liu
 * @since 2020/9/28
 */
public enum HttpStateCodeEnum implements CodeEnum<Integer> {
    SUCCESS(200),

    BUSINESS_ERROR(400),

    NO_LOGIN(401),

    NO_PERMISSION(403),

    ILLEGAL_PARAM(405),

    SYSTEM_ERROR(500);

    int code;

    HttpStateCodeEnum(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
