package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 用户对菜品味道评分
 *
 * @author heng.liu
 * @since 2020/12/28
 */
public enum CuisineTasteEnum implements CodeEnum<Integer> {

    UNEVALUATED(0, "未评价", 1),

    YUMMY(1, "好吃", 3),

    UNPALATABLE(2, "不好吃", -1),

    ;

    private final int code;

    private final String desc;

    private final int score;

    CuisineTasteEnum(int code, String desc, int score) {
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
