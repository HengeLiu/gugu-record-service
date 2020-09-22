package com.nutrition.nutritionservice.enums;

public class CodeEnums {
    public CodeEnums() {
    }

    public static <E extends CodeEnum<?>> E valueOf(Class<E> clazz, Object code) {
        E[] codeEnums = clazz.getEnumConstants();
        for (E codeEnum : codeEnums) {
            if (codeEnum.getCode().equals(code)) {
                return codeEnum;
            }
        }
        return null;
    }
}