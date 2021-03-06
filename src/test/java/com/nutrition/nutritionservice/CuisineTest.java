package com.nutrition.nutritionservice;

import com.google.common.collect.Lists;
import com.nutrition.nutritionservice.biz.CuisineBiz;
import com.nutrition.nutritionservice.biz.ModelIngredientCategoryModelBiz;
import com.nutrition.nutritionservice.controller.ao.CuisineDesignerAo;
import com.nutrition.nutritionservice.enums.database.CuisineCategoryEnum;
import com.nutrition.nutritionservice.enums.database.CuisineStatusEnum;
import com.nutrition.nutritionservice.enums.database.CuisineWarmEnum;
import com.nutrition.nutritionservice.enums.database.DineTimeEnum;
import com.nutrition.nutritionservice.enums.database.IngredientProcessEnum;
import com.nutrition.nutritionservice.service.CuisineIngredientRelService;
import com.nutrition.nutritionservice.service.CuisineNutrientWeightService;
import com.nutrition.nutritionservice.service.CuisineService;
import com.nutrition.nutritionservice.vo.CuisineNutrientWeightVo;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientCategoryModelVo;
import com.nutrition.nutritionservice.vo.CuisineIngredientRelVo;
import com.nutrition.nutritionservice.vo.CuisineVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author heng.liu
 * @since 2020/12/25
 */
@SpringBootTest
public class CuisineTest {

    @Resource
    private CuisineBiz cuisineBiz;

    @Resource
    private CuisineService cuisineService;

    @Resource
    private ModelIngredientCategoryModelBiz modelIngredientCategoryModelBiz;

    @Resource
    private CuisineIngredientRelService cuisineIngredientRelService;

    @Resource
    private CuisineNutrientWeightService cuisineNutrientWeightService;

    @Test
    public void createNewCuisine() {
//        DineTimeEnum dineTimeEnum = DineTimeEnum.BREAKFAST;
//         DineTimeEnum dineTimeEnum = DineTimeEnum.LUNCH;
         DineTimeEnum dineTimeEnum = DineTimeEnum.DINNER;
        CuisineWarmEnum cuisineWarmEnum = CuisineWarmEnum.WARM;
        ModelIngredientCategoryModelVo modelIngredientCategoryModelVo = modelIngredientCategoryModelBiz.queryMostNeededModel();
        Map<String, Integer> recommendedWeightMap = cuisineBiz
                .queryRecommendedCategoryWeightMap(modelIngredientCategoryModelVo, dineTimeEnum.getCode());
        Map<String, List<IngredientVo>> ingredientCategoryMap = cuisineBiz.queryIngredientCategoryMap();
        for (int i = 0; i < 10; i++) {
            List<CuisineIngredientRelVo> cuisineIngredientRelVoList = Lists.newArrayList();
            ingredientCategoryMap.forEach((categoryCode, ingredientList) -> {
                int ingredientIndex = (int) (Math.random() * ingredientList.size());
                cuisineIngredientRelVoList.add(
                        CuisineIngredientRelVo.builder()
                        .ingredientCode(ingredientList.get(ingredientIndex).getCode())
                        .process(IngredientProcessEnum.STEW.getCode())
                        .weight(recommendedWeightMap.getOrDefault(categoryCode, 0)).build());
            });
            cuisineBiz.addNewCuisine(CuisineDesignerAo.builder()
                    .cuisineVo(CuisineVo.builder()
                            .warm(cuisineWarmEnum.getCode()).status(CuisineStatusEnum.SALE.getCode())
                            .cuisineType(CuisineCategoryEnum.SET.getCode())
                            .name("测试菜" + (40 + i)).dineTime(dineTimeEnum.getCode()).storeCode("100001")
                            .goal(modelIngredientCategoryModelVo.getGoal()).build())
                    .cuisineIngredientRelList(cuisineIngredientRelVoList).build());
        }

    }

    @Test
//    @Transactional(rollbackFor = Exception.class)
    public void saveCuisineNutrient() {
        List<CuisineVo> cuisineVos = cuisineService.queryByStoreCode("100001");
        for (CuisineVo cuisineVo : cuisineVos) {
            // 餐品食材重量
            Map<Integer, Integer> ingredientCodeWeightMap = cuisineIngredientRelService
                    .queryByCuisineCode(cuisineVo.getCode()).stream().collect(Collectors
                            .toMap(CuisineIngredientRelVo::getIngredientCode, CuisineIngredientRelVo::getWeight));
            List<CuisineNutrientWeightVo> cuisineNutrientWeightVoList = cuisineBiz
                    .ingredientWeightToNutrientWeightVo(ingredientCodeWeightMap, cuisineVo.getCode());
            cuisineNutrientWeightService.addAll(cuisineNutrientWeightVoList);
        }
    }

}
