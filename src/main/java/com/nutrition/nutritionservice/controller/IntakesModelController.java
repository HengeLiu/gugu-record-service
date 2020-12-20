package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.IntakesModelBiz;
import com.nutrition.nutritionservice.vo.ModelParamVo;
import com.nutrition.nutritionservice.vo.IntakesModelVo;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientIntakesVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 模型相关。
 *
 * @author heng.liu
 * @since 2020/9/13
 */
@RestController
@RequestMapping("/intakes-model")
public class IntakesModelController {

    @Resource
    private IntakesModelBiz intakesModelBiz;

    @PostMapping("/calculate")
    public ModelIngredientIntakesVo calculateIntakesModel(@RequestBody ModelParamVo param) {
        return intakesModelBiz.calculateIntakesModel(param);
    }

    @PostMapping("/query")
    public IntakesModelVo queryIntakesModel(String uuid) {
        return null;
    }

}
