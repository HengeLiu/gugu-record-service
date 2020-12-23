package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.CuisineBiz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ModelAndView designer() {
        return new ModelAndView("cuisine_designer", "categoryList", cuisineBiz.queryIngredientAndCategory());
    }

}
