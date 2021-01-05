package com.nutrition.nutritionservice.enums.database;

import com.nutrition.nutritionservice.enums.CodeEnum;

/**
 * 营养素枚举
 *
 * @author heng.liu
 * @since 2021/1/5
 */
public enum NutrientEnum implements CodeEnum<Integer> {

    UNKNOWN(0, "未知", "unknown", MacronutrientEnum.UNKNOWN),

    Edible(1001, "食部", "Edible", MacronutrientEnum.UNKNOWN),

    Water(1002, "水分", "Water", MacronutrientEnum.UNKNOWN),

    Energy(1003, "能量", "Energy", MacronutrientEnum.UNKNOWN),

    Protein(1004, "蛋白质", "Protein", MacronutrientEnum.PROTEIN),

    Fat(1005, "脂肪", "Fat", MacronutrientEnum.LIPIDS),

    Cholesterol(1006, "胆固醇", "Cholesterol", MacronutrientEnum.LIPIDS),

    Ash(1007, "灰分", "Ash", MacronutrientEnum.UNKNOWN),

    CHO(1008, "碳水化合物", "CHO", MacronutrientEnum.CARBOHYDRATE),

    Fiber(1009, "总膳食纤维", "Dietary fiber", MacronutrientEnum.CARBOHYDRATE),

    Carotene(1010, "胡萝卜素", "Carotene", MacronutrientEnum.VITAMIN),

    VA(1011, "维生素A", "Vitamin A", MacronutrientEnum.VITAMIN),

    VE(1012, "维生素E", "Vitamin E", MacronutrientEnum.VITAMIN),

    Thiamine(1013, "硫胺素", "Thiamine", MacronutrientEnum.VITAMIN),

    Riboflavin(1014, "核黄素", "Riboflavin", MacronutrientEnum.VITAMIN),

    Niacin(1015, "烟酸", "Niacin", MacronutrientEnum.VITAMIN),

    VC(1016, "维生素C", "Vitamin C", MacronutrientEnum.VITAMIN),

    Ca(1017, "钙", "Ca", MacronutrientEnum.MINERAL),

    P(1018, "磷", "P", MacronutrientEnum.MINERAL),

    K(1019, "钾", "K", MacronutrientEnum.MINERAL),

    Na(1020, "钠", "Na", MacronutrientEnum.MINERAL),

    Mg(1021, "镁", "Mg", MacronutrientEnum.MINERAL),

    Fe(1022, "铁", "Fe", MacronutrientEnum.MINERAL),

    Zn(1023, "锌", "Zn", MacronutrientEnum.MINERAL),

    Se(1024, "硒", "Se", MacronutrientEnum.MINERAL),

    Cu(1025, "铜", "Cu", MacronutrientEnum.MINERAL),

    Mn(1026, "锰", "Mn", MacronutrientEnum.MINERAL),

    I(1027, "碘", "I", MacronutrientEnum.MINERAL),

    SFA(1028, "饱和脂肪酸", "SFA", MacronutrientEnum.LIPIDS),

    MFA(1029, "单不饱和脂肪酸", "MFA", MacronutrientEnum.LIPIDS),

    PFA(1030, "多不饱和脂肪酸", "PFA", MacronutrientEnum.LIPIDS),

    ;

    private final int code;

    private final String nameZh;
    private final String nameEn;
    private final MacronutrientEnum macronutrient;

    NutrientEnum(int code, String nameZh, String nameEn, MacronutrientEnum macronutrient) {
        this.code = code;
        this.nameZh = nameZh;
        this.nameEn = nameEn;
        this.macronutrient = macronutrient;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public String getNameZh() {
        return nameZh;
    }

    public String getNameEn() {
        return nameEn;
    }

    public MacronutrientEnum getMacronutrient() {
        return macronutrient;
    }
}
