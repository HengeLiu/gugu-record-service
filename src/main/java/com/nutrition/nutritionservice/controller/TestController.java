package com.nutrition.nutritionservice.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heng.liu
 * @since 2020/9/13
 */
@RestController
@EnableAutoConfiguration
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "Welcome to Nutrition service.";
    }

}
