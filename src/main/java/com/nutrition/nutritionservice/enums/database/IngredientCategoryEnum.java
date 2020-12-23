package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 食材类别枚举。
 *
 * @author heng.liu
 * @since 2020/9/21
 */
public enum IngredientCategoryEnum implements CodeEnum<Integer> {

    PROCESSED_GRAINS(1, "processed_grains", "精制谷物", IngredientSuperCategoryEnum.GRAINS_POTATOES),

    UNPROCESSED_GRAINS(2, "unprocessed_grains", "全谷物", IngredientSuperCategoryEnum.GRAINS_POTATOES),

    MIXED_BEANS(3, "mixed_beans", "杂豆", IngredientSuperCategoryEnum.GRAINS_POTATOES),

    TUBER(4, "tuber", "薯类", IngredientSuperCategoryEnum.GRAINS_POTATOES),

    GENERAL_VEGETABLES(5, "general_vegetables", "一般蔬菜", IngredientSuperCategoryEnum.VEGETABLES_FRUITS),

    DARK_VEGETABLES(6, "dark_vegetables", "深色蔬菜", IngredientSuperCategoryEnum.VEGETABLES_FRUITS),

    FRUITS(7, "fruits", "水果", IngredientSuperCategoryEnum.VEGETABLES_FRUITS),

    MEAT(8, "meat", "禽肉", IngredientSuperCategoryEnum.FISH_POULTRY_EGGS),

    POULTRY(9, "poultry", "畜肉", IngredientSuperCategoryEnum.FISH_POULTRY_EGGS),

    aquatic(10, "aquatic", "水产品", IngredientSuperCategoryEnum.FISH_POULTRY_EGGS),

    EGGS(11, "egg", "蛋", IngredientSuperCategoryEnum.FISH_POULTRY_EGGS),

    DAIRY(12, "dairy", "乳", IngredientSuperCategoryEnum.DAIRY_BEANS_NUTS),

    SOYBEAN(13, "soybean", "大豆", IngredientSuperCategoryEnum.DAIRY_BEANS_NUTS),

    NUT(14, "nut", "坚果", IngredientSuperCategoryEnum.DAIRY_BEANS_NUTS),

    OIL(15, "oil", "烹调油", IngredientSuperCategoryEnum.CONDIMENTS),

    SALT(16, "salt", "食盐", IngredientSuperCategoryEnum.CONDIMENTS),

    SUGAR(17, "sugar", "糖", IngredientSuperCategoryEnum.CONDIMENTS),

    ;

    private final int code;

    private final String nameEn;

    private final String nameZh;

    private final IngredientSuperCategoryEnum parentCategory;

    IngredientCategoryEnum(int code, String nameEn, String nameZh, IngredientSuperCategoryEnum parentCategory) {
        this.code = code;
        this.nameEn = nameEn;
        this.nameZh = nameZh;
        this.parentCategory = parentCategory;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameZh() {
        return nameZh;
    }

    public IngredientSuperCategoryEnum getParentCategory() {
        return parentCategory;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
