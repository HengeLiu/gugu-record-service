package com.nutrition.nutritionservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

}
