package com.nutrition.nutritionservice.enums;

/**
 * 职业活动水平枚举。
 * 
 * @author heng.liu
 * @since 2020/12/18
 */
public enum ProfeCharEnum implements CodeEnum<Integer> {

    ALWAYS_SITTING(0, "久坐", 0), BALANCED(1, "坐站均衡", 1), ALWAYS_STANDING(2, "久站", 2), HEAVY_WORK(3, "重体力劳动", 3)

    ;

    private final int code;

    private final String desc;

    private final int score;

    ProfeCharEnum(int code, String desc, int score) {
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
