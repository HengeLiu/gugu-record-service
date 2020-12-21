package com.nutrition.nutritionservice.enums;

/**
 * 用户账号类型枚举。
 * 
 * @author heng.liu
 * @since 2020/9/23
 */
public enum UserAccountTypeEnum implements CodeEnum<Integer> {
    OWN(0),

    WEI_XIN(1);

    private final int code;

    UserAccountTypeEnum(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
