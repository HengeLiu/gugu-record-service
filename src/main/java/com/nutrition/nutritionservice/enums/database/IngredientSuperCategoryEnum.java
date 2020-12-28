package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 食材大类别枚举。
 * 
 * @author heng.liu
 * @since 2020/9/21
 */
public enum IngredientSuperCategoryEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "unknown", "未知"),

    GRAINS_POTATOES(1, "grains_potatoes", "谷薯类"),

    VEGETABLES_FRUITS(2, "vegetables_fruits", "蔬菜水果"),

    FISH_POULTRY_EGGS(3, "fish_poultry_eggs", "鱼畜禽蛋"),

    DAIRY_BEANS_NUTS(4, "dairy_beans_nuts", "乳豆坚果"),

    CONDIMENTS(5, "condiments", "调味品"),

    ;

    private final int code;

    private final String nameEn;

    private final String nameZh;

    IngredientSuperCategoryEnum(int code, String nameEn, String nameZh) {
        this.code = code;
        this.nameEn = nameEn;
        this.nameZh = nameZh;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameZh() {
        return nameZh;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
