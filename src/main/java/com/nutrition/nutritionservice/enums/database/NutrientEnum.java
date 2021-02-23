package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;
import lombok.Getter;

/**
 * 营养素枚举
 *
 * @author heng.liu
 * @since 2021/1/5
 */
@Getter
public enum NutrientEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "未知", "unknown", MacronutrientEnum.UNKNOWN, "", false),

    Edible(1001, "食部", "Edible", MacronutrientEnum.UNKNOWN, "", false),

    Water(1002, "水分", "Water", MacronutrientEnum.UNKNOWN, "", false),

    Energy(1003, "能量", "Energy", MacronutrientEnum.UNKNOWN, "", false),

    Protein(1004, "蛋白质", "Protein", MacronutrientEnum.PROTEIN, "", false),

    Fat(1005, "脂肪", "Fat", MacronutrientEnum.LIPIDS, "", false),

    Cholesterol(1006, "胆固醇", "Cholesterol", MacronutrientEnum.LIPIDS, "", true),

    Ash(1007, "灰分", "Ash", MacronutrientEnum.UNKNOWN, "", false),

    CHO(1008, "碳水化合物", "CHO", MacronutrientEnum.CARBOHYDRATE, "", false),

    Fiber(1009, "总膳食纤维", "Dietary fiber", MacronutrientEnum.CARBOHYDRATE, "", true),

    Carotene(1010, "胡萝卜素", "Carotene", MacronutrientEnum.VITAMIN, "", true),

    VA(1011, "维生素A", "Vitamin A", MacronutrientEnum.VITAMIN, "", true),

    VE(1012, "维生素E", "Vitamin E", MacronutrientEnum.VITAMIN, "", true),

    Thiamine(1013, "硫胺素", "Thiamine", MacronutrientEnum.VITAMIN, "", true),

    Riboflavin(1014, "核黄素", "Riboflavin", MacronutrientEnum.VITAMIN, "", true),

    Niacin(1015, "烟酸", "Niacin", MacronutrientEnum.VITAMIN, "", true),

    VC(1016, "维生素C", "Vitamin C", MacronutrientEnum.VITAMIN, "", true),

    Ca(1017, "钙", "Ca", MacronutrientEnum.MINERAL, "", true),

    P(1018, "磷", "P", MacronutrientEnum.MINERAL, "", true),

    K(1019, "钾", "K", MacronutrientEnum.MINERAL, "", true),

    Na(1020, "钠", "Na", MacronutrientEnum.MINERAL, "", true),

    Mg(1021, "镁", "Mg", MacronutrientEnum.MINERAL, "", true),

    Fe(1022, "铁", "Fe", MacronutrientEnum.MINERAL, "", true),

    Zn(1023, "锌", "Zn", MacronutrientEnum.MINERAL, "", true),

    Se(1024, "硒", "Se", MacronutrientEnum.MINERAL, "", true),

    Cu(1025, "铜", "Cu", MacronutrientEnum.MINERAL, "", true),

    Mn(1026, "锰", "Mn", MacronutrientEnum.MINERAL, "", true),

    I(1027, "碘", "I", MacronutrientEnum.MINERAL, "", true),

    SFA(1028, "饱和脂肪酸", "SFA", MacronutrientEnum.LIPIDS, "", false),

    MFA(1029, "单不饱和脂肪酸", "MFA", MacronutrientEnum.LIPIDS, "", false),

    PFA(1030, "多不饱和脂肪酸", "PFA", MacronutrientEnum.LIPIDS, "", false),

    ;

    private final int code;

    private final String nameZh;
    private final String nameEn;
    private final MacronutrientEnum macronutrient;
    private final String mainIngredientNameListStr;

    /**
     * 是否可被关注
     */
    private final boolean canConcern;

    NutrientEnum(int code, String nameZh, String nameEn, MacronutrientEnum macronutrient,
            String mainIngredientNameListStr, boolean canConcern) {
        this.code = code;
        this.nameZh = nameZh;
        this.nameEn = nameEn;
        this.macronutrient = macronutrient;
        this.mainIngredientNameListStr = mainIngredientNameListStr;
        this.canConcern = canConcern;
    }

    public Integer getCode() {
        return code;
    }
}
