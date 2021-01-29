package com.nutrition.nutritionservice.util;

import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * 餐品工具
 *
 * @author heng.liu
 * @since 2021/1/24
 */
public class CuisineUtil {

    public static String ingredientListToStr(List<String> ingredientNameList) {
        if (ingredientNameList.size() > 7) {
            ingredientNameList = ingredientNameList.subList(0, 7);
        }
        return StringUtils.concat(ingredientNameList).replaceAll(",", " ")
                .replace("[", "").replace("]", "") + "等";
    }
}
