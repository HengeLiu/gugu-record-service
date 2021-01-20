package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.IngredientBiz;
import com.nutrition.nutritionservice.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 食材
 *
 * @author heng.liu
 * @since 2021/1/20
 */
@Slf4j
@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    @Resource
    private IngredientBiz ingredientBiz;

    @GetMapping("/query/available")
    @ResponseBody
    public Response queryAvailable() {
        return Response.success(ingredientBiz.queryAvailable());
    }

}
