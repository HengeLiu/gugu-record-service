package com.nutrition.nutritionservice.enums;

/**
 * 食材子类别枚举。
 *
 * @author heng.liu
 * @since 2020/9/21
 */
public enum IngredientSubCategoryEnum implements CodeEnum<Integer> {

    REFINED_GRAINS(1, "refined_grains", "精制谷物", IngredientCategoryEnum.GRAINS_POTATOES),

    WHOLE_GRAINS_BEANS(2, "whole_grains_beans", "全谷物及杂豆", IngredientCategoryEnum.GRAINS_POTATOES),

    TUBER(3, "tubers", "薯类", IngredientCategoryEnum.GRAINS_POTATOES),

    GENERAL_VEGETABLES(4, "general_vegetables", "一般蔬菜", IngredientCategoryEnum.VEGETABLES_FRUITS),

    DARK_VEGETABLES(5, "dark_vegetables", "深色蔬菜", IngredientCategoryEnum.VEGETABLES_FRUITS),

    FRUITS(6, "fruits", "水果", IngredientCategoryEnum.VEGETABLES_FRUITS),

    LIVESTOCK_POULTRY_MEAT(7, "livestock_poultry_meat", "畜禽肉类", IngredientCategoryEnum.FISH_POULTRY_EGGS),

    EGGS(8, "egg", "蛋类", IngredientCategoryEnum.FISH_POULTRY_EGGS),

    AQUATICS(9, "aquatics", "水产品", IngredientCategoryEnum.FISH_POULTRY_EGGS),

    DAIRY(10, "dairy", "乳制品", IngredientCategoryEnum.DAIRY_BEANS_NUTS),

    SOYBEANS(11, "soybeans", "大豆", IngredientCategoryEnum.DAIRY_BEANS_NUTS),

    NUTS(12, "nuts", "坚果", IngredientCategoryEnum.DAIRY_BEANS_NUTS),

    COOKING_OIL(13, "cooking_oil", "烹调油", IngredientCategoryEnum.OIL_SALT),

    SALT(14, "salt", "食盐", IngredientCategoryEnum.OIL_SALT),

    ;

    private final Integer code;

    private final String enName;

    private final String zhName;

    private final IngredientCategoryEnum parentCategory;

    IngredientSubCategoryEnum(Integer code, String enName, String zhName, IngredientCategoryEnum parentCategory) {
        this.code = code;
        this.enName = enName;
        this.zhName = zhName;
        this.parentCategory = parentCategory;
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

    public IngredientCategoryEnum getParentCategory() {
        return parentCategory;
    }
}
