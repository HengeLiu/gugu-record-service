package com.nutrition.nutritionservice.enums;

/**
 * 食材类别枚举。
 *
 * @author heng.liu
 * @since 2020/9/21
 */
public enum IngredientSubCategoryEnum implements CodeEnum<String> {

    PROCESSED_GRAINS("processed_grains", "精制谷物", IngredientSuperCategoryEnum.GRAINS_POTATOES),

    UNPROCESSED_GRAINS("unprocessed_grains", "全谷物", IngredientSuperCategoryEnum.GRAINS_POTATOES),

    MIXED_BEANS("mixed_beans", "杂豆", IngredientSuperCategoryEnum.GRAINS_POTATOES),

    TUBER("tuber", "薯类", IngredientSuperCategoryEnum.GRAINS_POTATOES),

    GENERAL_VEGETABLES("general_vegetables", "一般蔬菜", IngredientSuperCategoryEnum.VEGETABLES_FRUITS),

    DARK_VEGETABLES("dark_vegetables", "深色蔬菜", IngredientSuperCategoryEnum.VEGETABLES_FRUITS),

    FRUITS("fruits", "水果", IngredientSuperCategoryEnum.VEGETABLES_FRUITS),

    MEAT("meat", "禽肉", IngredientSuperCategoryEnum.FISH_POULTRY_EGGS),

    POULTRY("poultry", "畜肉", IngredientSuperCategoryEnum.FISH_POULTRY_EGGS),

    aquatic("aquatic", "水产品", IngredientSuperCategoryEnum.FISH_POULTRY_EGGS),

    EGGS("egg", "蛋", IngredientSuperCategoryEnum.FISH_POULTRY_EGGS),

    DAIRY("dairy", "乳制品", IngredientSuperCategoryEnum.DAIRY_BEANS_NUTS),

    SOYBEAN("soybean", "大豆", IngredientSuperCategoryEnum.DAIRY_BEANS_NUTS),

    NUT("nut", "坚果", IngredientSuperCategoryEnum.DAIRY_BEANS_NUTS),

    OIL("oil", "烹调油", IngredientSuperCategoryEnum.OIL_SALT),

    SALT("salt", "食盐", IngredientSuperCategoryEnum.OIL_SALT),

    ;

    private final String code;


    private final String desc;

    private final IngredientSuperCategoryEnum parentCategory;

    IngredientSubCategoryEnum(String code, String desc, IngredientSuperCategoryEnum parentCategory) {
        this.code = code;
        this.desc = desc;
        this.parentCategory = parentCategory;
    }

    @Override
    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public IngredientSuperCategoryEnum getParentCategory() {
        return parentCategory;
    }
}
