package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.CuisineBiz;
import com.nutrition.nutritionservice.vo.store.CuisineWebAo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author heng.liu
 * @since 2020/12/23
 */
@Controller
@RequestMapping("/cuisine")
public class CuisineController {

    @Resource
    private CuisineBiz cuisineBiz;

    @GetMapping("/designer")
    public ModelAndView designer(@RequestParam("dine") Integer dineCode) {
        ModelAndView model = new ModelAndView("cuisine_designer");
        model.addObject("ingredientCategoryMap", cuisineBiz.queryIngredientCategoryMap());
        model.addObject("recommendedCategoryWeightMap", cuisineBiz.queryRecommendedCategoryWeightMap(dineCode));
        model.addObject("cuisine", CuisineWebAo.builder().build());
        return model;
    }

}
