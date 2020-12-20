package com.nutrition.nutritionservice;

import com.alibaba.fastjson.JSON;
import com.nutrition.nutritionservice.biz.IntakesModelBiz;
import com.nutrition.nutritionservice.vo.ModelParamVo;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientIntakesVo;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author heng.liu
 * @since 2020/9/22
 */
@SpringBootTest
public class IntakesModelBizTests {

    @Resource
    private IntakesModelBiz intakesModelBiz;

    @Test
    public void calculateIntakesModel() {
        ModelIngredientIntakesVo modelIngredientIntakesVo = intakesModelBiz.calculateIntakesModel(
                ModelParamVo.builder().age(26).gender(1).sportHabits(0).profeChar(0).weight(60).goal(1).build());
        System.out.println(JSON.toJSONString(modelIngredientIntakesVo));
    }

}
