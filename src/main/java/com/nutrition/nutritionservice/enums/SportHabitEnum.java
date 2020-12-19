package com.nutrition.nutritionservice.enums;

/**
 * 运动习惯枚举。
 *
 * @author heng.liu
 * @since 2020/12/18
 */
public enum SportHabitEnum implements CodeEnum<Integer> {

    NEVER(0, "从不运动", 0),

    OCCASIONAL_ACTIVITY(1, "", 1),

    OCCASIONAL_EXERCISE(2, "", 2),

    FREQUENT_EXERCISE(3, "", 3),

    ;

    private final int code;

    private final String desc;

    private final int score;

    SportHabitEnum(int code, String desc, int score) {
        this.code = code;
        this.desc = desc;
        this.score = score;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public int getScore() {
        return score;
    }
}
