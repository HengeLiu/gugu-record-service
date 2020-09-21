package com.nutrition.nutritionservice.enums;

/**
 * 食材子类别枚举。
 *
 * @author heng.liu
 * @since 2020/9/21
 */
public enum IngredientSubCategoryEnum implements CodeEnum {

    REFINED_GRAINS("refined_grains", "精制谷物", IngredientCategoryEnum.GRAINS_POTATOES),

    WHOLE_GRAINS_BEANS("whole_grains_beans", "全谷物及杂豆", IngredientCategoryEnum.GRAINS_POTATOES),

    TUBER("tubers", "薯类", IngredientCategoryEnum.GRAINS_POTATOES),

    GENERAL_VEGETABLES("general_vegetables", "一般蔬菜", IngredientCategoryEnum.VEGETABLES_FRUITS),

    DARK_VEGETABLES("dark_vegetables", "深色蔬菜", IngredientCategoryEnum.VEGETABLES_FRUITS),

    FRUITS("fruits", "水果", IngredientCategoryEnum.VEGETABLES_FRUITS),

    LIVESTOCK_POULTRY_MEAT("livestock_poultry_meat", "畜禽肉类", IngredientCategoryEnum.FISH_POULTRY_EGGS),

    EGGS("egg", "蛋类", IngredientCategoryEnum.FISH_POULTRY_EGGS),

    AQUATICS("aquatics", "水产品", IngredientCategoryEnum.FISH_POULTRY_EGGS),

    DAIRY("dairy", "乳制品", IngredientCategoryEnum.DAIRY_BEANS_NUTS),

    SOYBEANS("soybeanS", "大豆", IngredientCategoryEnum.DAIRY_BEANS_NUTS),

    NUTS("nuts", "坚果", IngredientCategoryEnum.DAIRY_BEANS_NUTS),

    COOKING_OIL("cooking_oil", "烹调油", IngredientCategoryEnum.OIL_SALT),

    SALT("salt", "食盐", IngredientCategoryEnum.OIL_SALT),

    ;

    private final String code;

    private final String zhName;

    private final IngredientCategoryEnum parentCategory;

    IngredientSubCategoryEnum(String code, String zhName, IngredientCategoryEnum parentCategory) {
        this.code = code;
        this.zhName = zhName;
        this.parentCategory = parentCategory;
    }

    @Override
    public String getCode() {
        return code;
    }

    public String getZhName() {
        return zhName;
    }

    public IngredientCategoryEnum getParentCategory() {
        return parentCategory;
    }
}
