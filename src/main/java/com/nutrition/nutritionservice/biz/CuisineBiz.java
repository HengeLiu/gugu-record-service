package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.converter.ModelConverter;
import com.nutrition.nutritionservice.enums.database.IngredientCategoryEnum;
import com.nutrition.nutritionservice.service.CuisineService;
import com.nutrition.nutritionservice.service.IngredientService;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.nutrition.nutritionservice.vo.modeldata.IntakesModelVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Resource
    private IntakesModelBiz intakesModelBiz;

    public Map<String, List<IngredientVo>> queryIngredientCategoryMap() {
        Map<String, List<IngredientVo>> ingredientCategoryMap = Maps.newHashMap();
        for (IngredientCategoryEnum categoryEnum : IngredientCategoryEnum.values()) {
            ingredientCategoryMap.put(categoryEnum.getNameEn(),
                    ingredientService.queryByCategoryCode(categoryEnum.getCode()));
        }
        return ingredientCategoryMap;
    }

    public Map<String, Integer> queryRecommendedCategoryMap() {
        IntakesModelVo mostNeededModel = intakesModelBiz.queryMostNeededModel();
        Map<IngredientCategoryEnum, Integer> categoryEnumMap = ModelConverter.INSTANCE
                .modelToCategoryMap(mostNeededModel);
        return categoryEnumMap.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getNameEn(), Map.Entry::getValue));
    }

}
