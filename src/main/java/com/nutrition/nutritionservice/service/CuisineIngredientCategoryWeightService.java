package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.CuisineIngredientCategoryWeightDao;
import com.nutrition.nutritionservice.enums.CodeEnums;
import com.nutrition.nutritionservice.enums.database.IngredientCategoryEnum;
import com.nutrition.nutritionservice.util.ModelUtil;
import com.nutrition.nutritionservice.vo.CuisineIngredientCategoryWeightVo;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.nutrition.nutritionservice.controller.ao.CuisineDesignerAo;
import com.nutrition.nutritionservice.vo.store.CuisineIngredientRelVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.Collections;
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
public class CuisineIngredientCategoryWeightService {

    @Resource
    private IngredientService ingredientService;

    @Resource
    private CuisineIngredientCategoryWeightDao cuisineIngredientCategoryWeightDao;

    public CuisineIngredientCategoryWeightVo calculateCuisineCategoryWight(CuisineDesignerAo cuisineDesignerAo) {
        Map<Integer, Integer> ingredientWeightMap = cuisineDesignerAo.getCuisineIngredientRelList().stream()
                .collect(Collectors.toMap(CuisineIngredientRelVo::getIngredientCode, CuisineIngredientRelVo::getWeight,
                        Integer::sum));
        List<IngredientVo> ingredientVoList = ingredientService
                .queryByCodeList(cuisineDesignerAo.getCuisineIngredientRelList().stream()
                        .map(CuisineIngredientRelVo::getIngredientCode).collect(Collectors.toList()));
        Map<IngredientCategoryEnum, Integer> categoryWeightMap = ingredientVoList.stream()
                .collect(Collectors.groupingBy(ingredientVo -> CodeEnums.valueOf(IngredientCategoryEnum.class,
                        ingredientVo.getCategoryCode()),
                        Collectors
                                .summingInt(ingredient -> ingredientWeightMap.getOrDefault(ingredient.getCode(), 0))));
        CuisineIngredientCategoryWeightVo cuisineIngredientCategoryWeightVo = new CuisineIngredientCategoryWeightVo();
        ModelUtil.categoryEnumMapToModel(categoryWeightMap, cuisineIngredientCategoryWeightVo);
        cuisineIngredientCategoryWeightVo.setCuisineCode(cuisineDesignerAo.getCuisineVo().getCode());
        cuisineIngredientCategoryWeightVo.setCalorie(cuisineDesignerAo.getCuisineVo().getCalorie());
        return cuisineIngredientCategoryWeightVo;
    }

    public List<CuisineIngredientCategoryWeightVo> queryByCuisineCodeList(List<String> cuisineCodeList) {
        if (CollectionUtils.isEmpty(cuisineCodeList)){
            return Collections.emptyList();
        }
        return cuisineIngredientCategoryWeightDao.batchSelectByCuisineCodeList(cuisineCodeList);
    }

    @Nullable
    public CuisineIngredientCategoryWeightVo queryByCuisineCode(String cuisineCode) {
        return cuisineIngredientCategoryWeightDao.selectByCuisineCode(cuisineCode);
    }

    public void add(CuisineIngredientCategoryWeightVo cuisineIngredientCategoryWeightVo) {
        cuisineIngredientCategoryWeightDao.insert(cuisineIngredientCategoryWeightVo);
    }

    public void updateByCuisineCodeSelective(CuisineIngredientCategoryWeightVo cuisineIngredientCategoryWeightVo) {
        cuisineIngredientCategoryWeightDao.updateByCuisineCodeSelective(cuisineIngredientCategoryWeightVo);
    }

}
