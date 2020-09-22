package com.nutrition.nutritionservice;

import com.nutrition.nutritionservice.biz.IntakesModelBiz;
import com.nutrition.nutritionservice.vo.IntakesModelUserInfoParamVo;
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
        intakesModelBiz.calculateIntakesModel(
                IntakesModelUserInfoParamVo.builder().age(26).gender(1).sportLevel(2).stature(180).weight(60).build());
    }

}
