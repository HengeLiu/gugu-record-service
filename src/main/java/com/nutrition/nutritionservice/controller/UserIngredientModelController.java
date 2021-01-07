package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.UserIngredientModelBiz;
import com.nutrition.nutritionservice.vo.ModelParamVo;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientCategoryModelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/user-model")
@Slf4j
public class UserIngredientModelController {

    @Resource
    private UserIngredientModelBiz userIngredientModelBiz;

    @PostMapping("/calculate-by-param")
    public ModelIngredientCategoryModelVo calculateIntakesModel(@RequestBody ModelParamVo param) {
        log.info("Calculate model, model param {}", param);
        return userIngredientModelBiz.calculateIntakesModel(param);
    }

    @GetMapping("/query-most-needed")
    public ModelIngredientCategoryModelVo queryMostNeededModel() {
        return userIngredientModelBiz.queryMostNeededModel();
    }

}
