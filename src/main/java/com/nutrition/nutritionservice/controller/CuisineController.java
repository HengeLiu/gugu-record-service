package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.CuisineBiz;
import com.nutrition.nutritionservice.common.Response;
import com.nutrition.nutritionservice.controller.ao.CuisineDesignerAo;
import com.nutrition.nutritionservice.controller.ao.CuisineUploadAo;
import com.nutrition.nutritionservice.vo.CuisineRecommendedScoreWebAo;
import com.nutrition.nutritionservice.vo.IDPageParamVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientCategoryModelVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientWeightSumDailyVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/check-name")
    @ResponseBody
    public Response checkCuisineName(@RequestParam String cuisineName, @RequestParam String storeCode) {
        return Response.success(cuisineBiz.checkName(cuisineName, storeCode));
    }

    @PostMapping("/create")
    @ResponseBody
    public String create(@ModelAttribute("cuisine") CuisineDesignerAo cuisineDesignerAo) {
        return "Create success";
    }

    @PostMapping("/upload")
    @ResponseBody
    public Response upload(@RequestBody CuisineUploadAo cuisineUploadAo) {
        cuisineBiz.uploadCuisine(cuisineUploadAo);
        return Response.success("upload success");
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
    public Response queryCuisineList(@RequestParam String storeCode) {
        return Response.success(cuisineBiz.queryCuisineList(storeCode));
    }

    @GetMapping("/query/cuisine-list")
    @ResponseBody
    public Response queryCuisineListByStoreCode(@RequestParam String storeCode) {
        return Response.success(cuisineBiz.queryCuisineListByStoreCode(storeCode));
    }

    @GetMapping("/query-details")
    @ResponseBody
    public Response queryCuisineDetails(@RequestParam String cuisineCode) {
        return Response.success(cuisineBiz.queryCuisineDetails(cuisineCode));
    }

}
