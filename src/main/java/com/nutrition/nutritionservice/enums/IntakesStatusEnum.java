package com.nutrition.nutritionservice.enums;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 模型摄入状态枚举
 *
 * @author heng.liu
 * @since 2021/1/4
 */
public enum IntakesStatusEnum implements CodeEnum<Integer> {

    INSUFFICIENT(0, "不足"),

    SATISFIED(1, "满足"),

    EXCESSIVE(2, "超标"),

    ;

    private final int code;

    private final String desc;

    IntakesStatusEnum(int code, String desc) {
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
