package com.nutrition.nutritionservice.service;

import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.dao.CuisineCategoryWeightDao;
import com.nutrition.nutritionservice.enums.CodeEnums;
import com.nutrition.nutritionservice.enums.database.IngredientCategoryEnum;
import com.nutrition.nutritionservice.util.ModelUtil;
import com.nutrition.nutritionservice.vo.CuisineCategoryWeightVo;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.nutrition.nutritionservice.vo.store.CuisineAssemblyAo;
import com.nutrition.nutritionservice.vo.store.CuisineIngredientRelVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜品食材分类重量Service。
 *
 * @author heng.liu
 * @since 2020/12/28
 */
@Service
public class CuisineCategoryWeightService {

    @Resource
    private IngredientService ingredientService;

    @Resource
    private CuisineCategoryWeightDao cuisineCategoryWeightDao;

    public CuisineCategoryWeightVo calculateCuisineCategoryWight(CuisineAssemblyAo cuisineAssemblyAo) {
        Map<String, Integer> ingredientWeightMap = cuisineAssemblyAo.getCuisineIngredientRelList().stream()
                .collect(Collectors.toMap(CuisineIngredientRelVo::getCuisineCode, CuisineIngredientRelVo::getWeight,
                        Integer::sum));
        List<IngredientVo> ingredientVoList = ingredientService
                .queryByCodeList(cuisineAssemblyAo.getCuisineIngredientRelList().stream()
                        .map(CuisineIngredientRelVo::getCuisineCode).collect(Collectors.toList()));
        Map<IngredientCategoryEnum, List<IngredientVo>> ingredientCategoryMap = ingredientVoList.stream()
                .collect(Collectors.groupingBy(ingredientVo -> CodeEnums.valueOf(IngredientCategoryEnum.class,
                        Integer.parseInt(ingredientVo.getCode()))));
        Map<IngredientCategoryEnum, Integer> categoryWeightMap = Maps.newHashMap();
        ingredientCategoryMap.forEach(((ingredientCategoryEnum, categoryIngredientList) -> {
            Integer categoryWeight = categoryIngredientList.stream()
                    .mapToInt(ingredient -> ingredientWeightMap.getOrDefault(ingredient.getCode(), 0)).sum();
            categoryWeightMap.put(ingredientCategoryEnum, categoryWeight);
        }));
        CuisineCategoryWeightVo cuisineCategoryWeightVo = new CuisineCategoryWeightVo();
        ModelUtil.categoryEnumMapToModel(categoryWeightMap, cuisineCategoryWeightVo);
        cuisineCategoryWeightVo.setCuisineCode(cuisineAssemblyAo.getCuisineVo().getCode());
        return cuisineCategoryWeightVo;
    }

    public List<CuisineCategoryWeightVo> queryCategoryWeightList(List<String> cuisineCodeList) {
        return cuisineCategoryWeightDao.batchSelectByCuisineCodeList(cuisineCodeList);
    }

}
