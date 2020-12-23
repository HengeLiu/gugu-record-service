package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.IntakesModelBiz;
import com.nutrition.nutritionservice.vo.ModelParamVo;
import com.nutrition.nutritionservice.vo.modeldata.IntakesModelVo;
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
@RequestMapping("/model")
@Slf4j
public class IntakesModelController {

    @Resource
    private IntakesModelBiz intakesModelBiz;

    @PostMapping("/calculate-by-param")
    public IntakesModelVo calculateIntakesModel(@RequestBody ModelParamVo param) {
        log.info("Calculate model, model param {}", param);
        return intakesModelBiz.calculateIntakesModel(param);
    }

    @PostMapping("/calculate-by-uuid")
    public IntakesModelVo calculateIntakesModel(@RequestBody String uuid) {
        log.info("Calculate model, model param {}", uuid);
        return intakesModelBiz.calculateIntakesModelByUuid(uuid);
    }

    @GetMapping("/query-most-needed")
    public IntakesModelVo queryMostNeededModel() {
        return intakesModelBiz.queryMostNeededModel();
    }

}
