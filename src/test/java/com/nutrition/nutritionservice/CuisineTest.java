package com.nutrition.nutritionservice;

import com.google.common.collect.Lists;
import com.nutrition.nutritionservice.biz.CuisineBiz;
import com.nutrition.nutritionservice.biz.ModelIngredientCategoryModelBiz;
import com.nutrition.nutritionservice.enums.database.CuisineStatusEnum;
import com.nutrition.nutritionservice.enums.database.CuisineCategoryEnum;
import com.nutrition.nutritionservice.enums.database.CuisineWarmEnum;
import com.nutrition.nutritionservice.enums.database.DineTimeEnum;
import com.nutrition.nutritionservice.enums.database.IngredientProcessEnum;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientCategoryModelVo;
import com.nutrition.nutritionservice.controller.ao.CuisineDesignerAo;
import com.nutrition.nutritionservice.vo.store.CuisineIngredientRelVo;
import com.nutrition.nutritionservice.vo.store.CuisineVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author heng.liu
 * @since 2020/12/25
 */
@SpringBootTest
public class CuisineTest {

    @Resource
    private CuisineBiz cuisineBiz;

    @Resource
    private ModelIngredientCategoryModelBiz modelIngredientCategoryModelBiz;

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
            cuisineBiz.saveNewCuisine(CuisineDesignerAo.builder()
                    .cuisineVo(CuisineVo.builder()
                            .warm(cuisineWarmEnum.getCode()).status(CuisineStatusEnum.SALE.getCode())
                            .cuisineType(CuisineCategoryEnum.SET.getCode())
                            .name("测试菜" + (40 + i)).dineTime(dineTimeEnum.getCode()).storeCode("100001")
                            .goal(modelIngredientCategoryModelVo.getGoal()).build())
                    .cuisineIngredientRelList(cuisineIngredientRelVoList).build());
        }

    }

}
