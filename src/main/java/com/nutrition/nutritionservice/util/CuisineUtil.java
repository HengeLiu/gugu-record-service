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
        return StringUtils.concat(ingredientNameList.subList(0, 7)).replaceAll(",", " ")
                .replace("[", "").replace("]", "") + " ...";
    }
}
