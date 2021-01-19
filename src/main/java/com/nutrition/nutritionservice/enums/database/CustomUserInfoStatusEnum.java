package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 用户自定义健康信息输入状态
 *
 * @author heng.liu
 * @since 2021/1/19
 */
public enum CustomUserInfoStatusEnum implements CodeEnum<Integer> {

    UNENTERED(0, "未录入"),

    ENTERED(1, "已录入"),

    ;

    private final int code;

    private final String desc;

    CustomUserInfoStatusEnum(int code, String desc) {
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
