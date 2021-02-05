package com.nutrition.nutritionservice.util;

import com.nutrition.nutritionservice.vo.IngredientVo;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 餐品工具
 *
 * @author heng.liu
 * @since 2021/1/24
 */
public class CuisineUtil {

    public static String ingredientListToStr(List<IngredientVo> ingredientList) {
        if (ingredientList.size() > 7) {
            ingredientList = ingredientList.subList(0, 7);
        }
        return StringUtils.concat(ingredientList.stream().map(IngredientVo::getName).collect(Collectors.toList()))
                .replaceAll(",", " ")
                .replace("[", "").replace("]", "") + "等";
    }
}
