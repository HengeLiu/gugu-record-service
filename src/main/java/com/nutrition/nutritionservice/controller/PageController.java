package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.CuisineBiz;
import com.nutrition.nutritionservice.biz.IngredientBiz;
import com.nutrition.nutritionservice.biz.ModelIngredientCategoryModelBiz;
import com.nutrition.nutritionservice.biz.StoreBiz;
import com.nutrition.nutritionservice.controller.ao.CuisineDesignerAo;
import com.nutrition.nutritionservice.service.ConfigPropertiesService;
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

    @Resource
    private ConfigPropertiesService configPropertiesService;

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
        model.addObject("imageHostUrl", configPropertiesService.getImageHostUrl());
        return model;
    }

    @GetMapping("/list/store/cuisine")
    public ModelAndView listStoreCuisine(@RequestParam String storeCode) {
        ModelAndView model = new ModelAndView("store_cuisine");
        model.addObject("cuisineList", cuisineBiz.queryCuisineList(storeCode));
        model.addObject("imageHostUrl", configPropertiesService.getImageHostUrl());
        return model;
    }

    @GetMapping("/list/cuisine/details")
    public ModelAndView listCuisineDetails(@RequestParam String cuisineCode) {
        ModelAndView model = new ModelAndView("cuisine_details");
        model.addObject("cuisineDetail", cuisineBiz.queryCuisineDetails(cuisineCode));
        model.addObject("imageHostUrl", configPropertiesService.getImageHostUrl());
        return model;
    }

    @GetMapping("/modify/cuisine")
    public ModelAndView modifyCuisine(@RequestParam String cuisineCode) {
        ModelAndView model = new ModelAndView("cuisine_modify");
        model.addObject("storeList", storeBiz.queryAllSupportedStore());
        model.addObject("cuisineDetail", cuisineBiz.queryCuisineDetails(cuisineCode));
        model.addObject("availableIngredientList", ingredientBiz.queryAvailable());
        model.addObject("imageHostUrl", configPropertiesService.getImageHostUrl());
        return model;
    }

}
