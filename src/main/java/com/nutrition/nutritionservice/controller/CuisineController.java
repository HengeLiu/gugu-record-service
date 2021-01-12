package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.CuisineBiz;
import com.nutrition.nutritionservice.biz.ModelIngredientCategoryModelBiz;
import com.nutrition.nutritionservice.common.Response;
import com.nutrition.nutritionservice.controller.ao.CuisineDesignerAo;
import com.nutrition.nutritionservice.vo.CuisineRecommendedScoreWebAo;
import com.nutrition.nutritionservice.vo.IDPageParamVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientCategoryModelVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientWeightSumDailyVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author heng.liu
 * @since 2020/12/23
 */
@Controller
@RequestMapping("/cuisine")
public class CuisineController {

    @Resource
    private CuisineBiz cuisineBiz;

    @Resource
    private ModelIngredientCategoryModelBiz modelIngredientCategoryModelBiz;

    @GetMapping("/designer")
    public ModelAndView designer(@RequestParam("dine") Integer dineCode) {
        ModelAndView model = new ModelAndView("cuisine_designer");
        model.addObject("ingredientCategoryMap", cuisineBiz.queryIngredientCategoryMap());
        model.addObject("recommendedCategoryWeightMap",
                cuisineBiz.queryRecommendedCategoryWeightMap(modelIngredientCategoryModelBiz.queryMostNeededModel(), dineCode));
        model.addObject("cuisine", CuisineDesignerAo.builder().build());
        return model;
    }

    @PostMapping("/create")
    @ResponseBody
    public String create(@ModelAttribute("cuisine") CuisineDesignerAo cuisineDesignerAo) {
        return "Create success";
    }

    @PostMapping("/recommendedList/dine")
    @ResponseBody
    public List<CuisineRecommendedScoreWebAo> queryRecommendedCuisineList(UserIngredientCategoryModelVo userModel,
                                                                          int dineTime, IDPageParamVo pageParam) {
        return cuisineBiz.queryRecommendedCuisineListByDineTime(userModel, dineTime, pageParam);
    }

    @PostMapping("/recommendedList/historical")
    @ResponseBody
    public List<CuisineRecommendedScoreWebAo> queryRecommendedCuisineList(UserIngredientCategoryModelVo userModel,
                                                                          UserIngredientWeightSumDailyVo historicalWeightSumDaily, IDPageParamVo pageParam) {
        return cuisineBiz.queryRecommendedCuisineListByHistorical(userModel, historicalWeightSumDaily, pageParam);
    }

    @GetMapping("/cuisine-list")
    @ResponseBody
    public Response queryCuisineList(@RequestParam String storeCode, @RequestParam String uuid) {
        return Response.success(cuisineBiz.queryCuisineList(storeCode));
    }

    @GetMapping("/query-details")
    @ResponseBody
    public Response queryCuisineDetails(@RequestParam String cuisineCode, @RequestParam String uuid) {
        return Response.success(cuisineBiz.queryCuisineDetails(cuisineCode, uuid));
    }

}
