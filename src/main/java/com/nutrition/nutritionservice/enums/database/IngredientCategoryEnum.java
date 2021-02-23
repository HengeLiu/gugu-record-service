package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;
import lombok.Getter;

/**
 * 食材类别枚举。
 *
 * @author heng.liu
 * @since 2020/9/21
 */
@Getter
public enum IngredientCategoryEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "unknown", "未知", IngredientSuperCategoryEnum.UNKNOWN, "", false),

    PROCESSED_GRAINS(1, "processed_grains", "精制谷物", IngredientSuperCategoryEnum.GRAINS_POTATOES, "", true),

    UNPROCESSED_GRAINS(2, "unprocessed_grains", "全谷物", IngredientSuperCategoryEnum.GRAINS_POTATOES, "", true),

    MIXED_BEANS(3, "mixed_beans", "杂豆", IngredientSuperCategoryEnum.GRAINS_POTATOES, "", true),

    TUBER(4, "tuber", "薯类", IngredientSuperCategoryEnum.GRAINS_POTATOES, "", true),

    GENERAL_VEGETABLES(5, "general_vegetables", "一般蔬菜", IngredientSuperCategoryEnum.VEGETABLES_FRUITS, "", true),

    DARK_VEGETABLES(6, "dark_vegetables", "深色蔬菜", IngredientSuperCategoryEnum.VEGETABLES_FRUITS, "", true),

    FRUITS(7, "fruits", "水果", IngredientSuperCategoryEnum.VEGETABLES_FRUITS, "", true),

    MEAT(8, "meat", "畜肉", IngredientSuperCategoryEnum.FISH_POULTRY_EGGS, "", true),

    POULTRY(9, "poultry", "禽肉", IngredientSuperCategoryEnum.FISH_POULTRY_EGGS, "", true),

    AQUATIC(10, "aquatic", "水产品", IngredientSuperCategoryEnum.FISH_POULTRY_EGGS, "", true),

    EGGS(11, "egg", "蛋", IngredientSuperCategoryEnum.FISH_POULTRY_EGGS, "", true),

    DAIRY(12, "dairy", "乳", IngredientSuperCategoryEnum.DAIRY_BEANS_NUTS, "", true),

    SOYBEAN(13, "soybean", "大豆", IngredientSuperCategoryEnum.DAIRY_BEANS_NUTS, "", true),

    NUT(14, "nut", "坚果", IngredientSuperCategoryEnum.DAIRY_BEANS_NUTS, "", true),

    OIL(15, "oil", "烹调油", IngredientSuperCategoryEnum.CONDIMENTS, "", false),

    SALT(16, "salt", "食盐", IngredientSuperCategoryEnum.CONDIMENTS, "", false),

    // SUGAR(17, "sugar", "糖", IngredientSuperCategoryEnum.CONDIMENTS, "", true),

    ;

    private final int code;

    private final String nameEn;

    private final String nameZh;

    private final IngredientSuperCategoryEnum parentCategory;

    private final String commonIngredientNameListStr;

    /**
     * 是否可被关注
     */
    private final boolean canConcern;

    IngredientCategoryEnum(int code, String nameEn, String nameZh, IngredientSuperCategoryEnum parentCategory,
            String commonIngredientNameArrayStr, boolean canConcern) {
        this.code = code;
        this.nameEn = nameEn;
        this.nameZh = nameZh;
        this.parentCategory = parentCategory;
        this.commonIngredientNameListStr = commonIngredientNameArrayStr;
        this.canConcern = canConcern;
    }

    public Integer getCode() {
        return code;
    }

}
