package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.controller.ao.CuisineDesignerAo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author heng.liu
 * @since 2020/9/13
 */
@Controller
public class TestController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Welcome to the future of healthy life, Gugu nutrition service.";
    }

    @GetMapping("/index")
    public String index() {
        return "home";
    }

    @RequestMapping("/test")
    public String test(@ModelAttribute("cuisine") CuisineDesignerAo cuisineDesignerAo, Model model, String name) {
        return "home";
    }

}
