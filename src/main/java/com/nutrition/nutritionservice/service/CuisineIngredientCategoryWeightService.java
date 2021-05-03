package com.nutrition.nutritionservice.service;

import com.google.common.collect.Lists;
import com.nutrition.nutritionservice.dao.CuisineIngredientCategoryWeightDao;
import com.nutrition.nutritionservice.enums.CodeEnums;
import com.nutrition.nutritionservice.enums.database.IngredientCategoryEnum;
import com.nutrition.nutritionservice.util.ModelUtil;
import com.nutrition.nutritionservice.vo.CuisineIngredientCategoryWeightVo;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.nutrition.nutritionservice.controller.ao.CuisineDesignerAo;
import com.nutrition.nutritionservice.vo.CuisineIngredientRelVo;
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

    public CuisineIngredientCategoryWeightVo calculateIngredientCategoryWightWithCuisine(
            CuisineDesignerAo cuisineDesignerAo) {
        return calculateIngredientCategoryWightWithIngredientList(cuisineDesignerAo.getCuisineIngredientRelList(),
                cuisineDesignerAo.getCuisineVo().getCode(), cuisineDesignerAo.getCuisineVo().getCalorie());
    }

    public CuisineIngredientCategoryWeightVo calculateIngredientCategoryWightWithIngredientList(
            List<CuisineIngredientRelVo> cuisineIngredientRelList, String cuisineCode, double cuisineCalorie) {
        Map<Integer, Integer> ingredientWeightMap = cuisineIngredientRelList.stream().collect(Collectors
                .toMap(CuisineIngredientRelVo::getIngredientCode, CuisineIngredientRelVo::getWeight, Integer::sum));
        Map<IngredientCategoryEnum, Integer> categoryWeightMap = this
                .calculateIngredientCategoryWight(ingredientWeightMap);
        CuisineIngredientCategoryWeightVo cuisineIngredientCategoryWeightVo = new CuisineIngredientCategoryWeightVo();
        ModelUtil.categoryEnumMapToModel(categoryWeightMap, cuisineIngredientCategoryWeightVo);
        cuisineIngredientCategoryWeightVo.setCuisineCode(cuisineCode);
        cuisineIngredientCategoryWeightVo.setCalorie(cuisineCalorie);
        return cuisineIngredientCategoryWeightVo;
    }

    public Map<IngredientCategoryEnum, Integer> calculateIngredientCategoryWight(
            Map<Integer, Integer> ingredientWeightMap) {
        List<IngredientVo> ingredientVoList = ingredientService
                .queryByCodeList(Lists.newArrayList(ingredientWeightMap.keySet()));
        return ingredientVoList.stream().collect(Collectors.groupingBy(
                ingredientVo -> CodeEnums.valueOf(IngredientCategoryEnum.class, ingredientVo.getCategoryCode()),
                Collectors.summingInt(ingredient -> ingredientWeightMap.getOrDefault(ingredient.getCode(), 0))));
    }

    public List<CuisineIngredientCategoryWeightVo> queryByCuisineCodeList(List<String> cuisineCodeList) {
        if (CollectionUtils.isEmpty(cuisineCodeList)){
            return Collections.emptyList();
        }
        return cuisineIngredientCategoryWeightDao.batchSelectByCuisineCodeList(cuisineCodeList);
    }

    @Nullable
    public CuisineIngredientCategoryWeightVo queryByCuisineCode(String cuisineCode) {
        CuisineIngredientCategoryWeightVo cuisineIngredientCategoryWeightVo = cuisineIngredientCategoryWeightDao
                .selectByCuisineCode(cuisineCode);
        ModelUtil.fillValue(cuisineIngredientCategoryWeightVo, 0);
        return cuisineIngredientCategoryWeightVo;
    }

    public void add(CuisineIngredientCategoryWeightVo cuisineIngredientCategoryWeightVo) {
        cuisineIngredientCategoryWeightDao.insertSelective(cuisineIngredientCategoryWeightVo);
    }

    public void updateByCuisineCode(CuisineIngredientCategoryWeightVo cuisineIngredientCategoryWeightVo) {
        cuisineIngredientCategoryWeightDao.updateByCuisineCode(cuisineIngredientCategoryWeightVo);
    }

}
