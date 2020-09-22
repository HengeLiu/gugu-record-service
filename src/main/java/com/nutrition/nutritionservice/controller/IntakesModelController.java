package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.IntakesModelBiz;
import com.nutrition.nutritionservice.vo.IntakesModelUserInfoParamVo;
import com.nutrition.nutritionservice.vo.IntakesModelVo;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 模型相关。
 *
 * @author heng.liu
 * @since 2020/9/13
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/intakes-model")
public class IntakesModelController {

    @Resource
    private IntakesModelBiz intakesModelBiz;

    @PostMapping("/calculate")
    @ResponseBody
    public IntakesModelVo calculateIntakesModel(IntakesModelUserInfoParamVo param) {
        return intakesModelBiz.calculateIntakesModel(param);
    }

}
