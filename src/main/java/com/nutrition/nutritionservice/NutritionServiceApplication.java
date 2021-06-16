package com.nutrition.nutritionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.nutrition.nutritionservice.dao")
@EnableScheduling
public class NutritionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NutritionServiceApplication.class, args);
    }

}
