package com.nutrition.nutritionservice.enums;

/**
 * 模型目标枚举。
 * 
 * @author heng.liu
 * @since 2020/12/19
 */
public enum ModelGoalEnum implements CodeEnum<Integer> {
    
    BALANCE(0, "平衡"),

    LOSE_WEIGHT(1, "减脂"),

    INCREASED_MUSCLE(2, "增肌")

    ;

    private final int code;

    private final String desc;

    ModelGoalEnum(int code, String desc) {
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
