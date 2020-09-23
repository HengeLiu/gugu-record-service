package com.nutrition.nutritionservice.enums;

/**
 * 用户状态枚举。
 *
 * @author heng.liu
 * @since 2020/9/23
 */
public enum UserAccountStatusTypeEnum implements CodeEnum<Integer> {
    ENABLE(0),

    LOCKED(1),

    EXPIRE(2);

    private final int code;

    UserAccountStatusTypeEnum(int code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
