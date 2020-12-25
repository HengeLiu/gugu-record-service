package com.nutrition.nutritionservice;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.biz.CuisineBiz;
import com.nutrition.nutritionservice.biz.IntakesModelBiz;
import com.nutrition.nutritionservice.enums.database.DineTimeEnum;
import com.nutrition.nutritionservice.enums.database.IngredientProcessEnum;
import com.nutrition.nutritionservice.service.CuisineService;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.nutrition.nutritionservice.vo.modeldata.IntakesModelVo;
import com.nutrition.nutritionservice.vo.store.CuisineIngredientRelVo;
import com.nutrition.nutritionservice.vo.store.CuisineVo;
import com.nutrition.nutritionservice.vo.store.CuisineWebAo;
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
    private CuisineService cuisineService;

    @Resource
    private IntakesModelBiz intakesModelBiz;

    @Test
    public void createNewCuisine() {
        DineTimeEnum dineTimeEnum = DineTimeEnum.BREAKFAST;
        IntakesModelVo intakesModelVo = intakesModelBiz.queryMostNeededModel();
        Map<String, Integer> recommendedWeightMap = cuisineBiz
                .queryRecommendedCategoryWeightMap(dineTimeEnum.getCode());
        Map<String, List<IngredientVo>> ingredientCategoryMap = cuisineBiz.queryIngredientCategoryMap();
        List<CuisineIngredientRelVo> cuisineIngredientRelVoList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            String cuisineCode = String.valueOf(10001 + i);
            ingredientCategoryMap.forEach((categoryCode, ingredientList) -> {
                int ingredientIndex = (int) (Math.random() * ingredientList.size());
                cuisineIngredientRelVoList.add(CuisineIngredientRelVo.builder().cuisineCode(cuisineCode)
                        .ingredientCode(ingredientList.get(ingredientIndex).getCode())
                        .process(IngredientProcessEnum.STEW.getCode())
                        .weight(recommendedWeightMap.getOrDefault(categoryCode, 0)).build());
            });
            cuisineService.saveNewCuisine(CuisineWebAo.builder()
                    .cuisineVo(CuisineVo.builder().code(cuisineCode).calorie().warm().status().cuisineType()
                            .name("测试菜" + cuisineCode).dineTime(dineTimeEnum.getCode()).storeCode("100001")
                            .goal(intakesModelVo.getGoal()).build())
                    .cuisineIngredientRelList(cuisineIngredientRelVoList).build());
        }

    }

}
