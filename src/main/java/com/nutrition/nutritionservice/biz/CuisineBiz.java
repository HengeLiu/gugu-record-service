package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Lists;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.enums.database.IngredientCategoryEnum;
import com.nutrition.nutritionservice.service.CuisineService;
import com.nutrition.nutritionservice.service.IngredientService;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.sun.tools.javac.util.Pair;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜品。
 * 
 * @author heng.liu
 * @since 2020/12/23
 */
@Biz
public class CuisineBiz {

    @Resource
    private CuisineService cuisineService;

    @Resource
    private IngredientService ingredientService;

    public List<Pair<IngredientCategoryEnum, List<IngredientVo>>> queryIngredientAndCategory() {
        List<Pair<IngredientCategoryEnum, List<IngredientVo>>> categoryIngredientList = Lists.newArrayList();
        for (IngredientCategoryEnum categoryEnum : IngredientCategoryEnum.values()) {
            categoryIngredientList
                    .add(Pair.of(categoryEnum, ingredientService.queryByCategoryCode(categoryEnum.getCode())));
        }
        return categoryIngredientList;
    }

}
