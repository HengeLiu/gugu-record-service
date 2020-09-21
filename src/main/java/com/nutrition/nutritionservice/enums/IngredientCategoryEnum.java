package com.nutrition.nutritionservice.enums;

/**
 * 食材类别枚举。
 * 
 * @author heng.liu
 * @since 2020/9/21
 */
public enum IngredientCategoryEnum implements CodeEnum<Integer> {

    GRAINS_POTATOES(1, "grains_potatoes", "谷薯类"),

    VEGETABLES_FRUITS(2, "vegetables_fruits", "蔬菜水果"),

    FISH_POULTRY_EGGS(3, "fish_poultry_eggs", "鱼畜禽蛋"),

    DAIRY_BEANS_NUTS(4, "dairy_beans_nuts", "乳类大豆坚果"),

    OIL_SALT(5, "oil_salt", "油盐"),

    ;

    private final Integer code;

    private final String enName;

    private final String zhName;

    IngredientCategoryEnum(Integer code, String enName, String zhName) {
        this.code = code;
        this.enName = enName;
        this.zhName = zhName;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public String getEnName() {
        return enName;
    }

    public String getZhName() {
        return zhName;
    }
}
