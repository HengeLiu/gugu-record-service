package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.CuisineBiz;
import com.nutrition.nutritionservice.biz.IngredientBiz;
import com.nutrition.nutritionservice.biz.ModelIngredientCategoryModelBiz;
import com.nutrition.nutritionservice.biz.StoreBiz;
import com.nutrition.nutritionservice.controller.ao.CuisineDesignerAo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 页面
 *
 * @author heng.liu
 * @since 2021/1/19
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @Resource
    private CuisineBiz cuisineBiz;

    @Resource
    private ModelIngredientCategoryModelBiz modelIngredientCategoryModelBiz;

    @Resource
    private StoreBiz storeBiz;

    @Resource
    private IngredientBiz ingredientBiz;

    @RequestMapping("/cuisine/design")
    public ModelAndView cuisineDesigner(@RequestParam("dine") Integer dineCode) {
        ModelAndView model = new ModelAndView("cuisine_designer");
        model.addObject("ingredientCategoryMap", cuisineBiz.queryIngredientCategoryMap());
        model.addObject("recommendedCategoryWeightMap", cuisineBiz
                .queryRecommendedCategoryWeightMap(modelIngredientCategoryModelBiz.queryMostNeededModel(), dineCode));
        model.addObject("cuisine", CuisineDesignerAo.builder().build());
        return model;
    }

    @GetMapping("/cuisine/upload")
    public ModelAndView cuisineUpload() {
        ModelAndView model = new ModelAndView("cuisine_upload");
        model.addObject("storeList", storeBiz.queryAllSupportedStore());
        model.addObject("ingredientList", ingredientBiz.queryAvailable());
        return model;
    }

}
