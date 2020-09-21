package com.nutrition.nutritionservice.enums;

/**
 * 食材类别枚举。
 * 
 * @author heng.liu
 * @since 2020/9/21
 */
public enum IngredientCategoryEnum implements CodeEnum {

    GRAINS_POTATOES("grains_potatoes", "谷薯类"),

    VEGETABLES_FRUITS("vegetables_fruits", "蔬菜水果"),

    FISH_POULTRY_EGGS("fish_poultry_eggs", "鱼畜禽蛋"),

    DAIRY_BEANS_NUTS("dairy_beans_nuts", "乳类大豆坚果"),

    OIL_SALT("oil_salt", "油盐"),

    ;

    private final String code;

    private final String zhName;

    IngredientCategoryEnum(String code, String zhName) {
        this.code = code;
        this.zhName = zhName;
    }

    @Override
    public String getCode() {
        return code;
    }

    public String getZhName() {
        return zhName;
    }
}
