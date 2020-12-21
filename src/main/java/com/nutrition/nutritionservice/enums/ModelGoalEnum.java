package com.nutrition.nutritionservice.enums;

/**
 * 模型目标枚举。
 * 
 * @author heng.liu
 * @since 2020/12/19
 */
public enum ModelGoalEnum implements CodeEnum<Integer> {
    
    UNKNOWN(0, "未知"),

    BALANCE(1, "平衡"),

    LOSE_WEIGHT(2, "减脂"),

    INCREASED_MUSCLE(3, "增肌")

    ;

    private final int code;

    private final String desc;

    ModelGoalEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
