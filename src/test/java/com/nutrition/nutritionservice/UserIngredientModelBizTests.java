package com.nutrition.nutritionservice;

import com.alibaba.fastjson.JSON;
import com.nutrition.nutritionservice.biz.ModelIngredientCategoryModelBiz;
import com.nutrition.nutritionservice.vo.ModelParamVo;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientCategoryModelVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author heng.liu
 * @since 2020/9/22
 */
@SpringBootTest
public class UserIngredientModelBizTests {

    @Resource
    private ModelIngredientCategoryModelBiz modelIngredientCategoryModelBiz;

    @Test
    public void calculateIntakesModel() {
        ModelIngredientCategoryModelVo modelIngredientCategoryModelVo = modelIngredientCategoryModelBiz.calculateIngredientModel(
                ModelParamVo.builder().age(26).gender(1).sportsHabits(0).profeChar(0).weight(60).goal(1).build());
        System.out.println(JSON.toJSONString(modelIngredientCategoryModelVo));
    }

}
