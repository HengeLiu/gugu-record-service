package com.nutrition.nutritionservice.exception;

/**
 * 健康服务通用异常。
 * 
 * @author heng.liu
 * @since 2020/9/23
 */
public class NutritionServiceException extends RuntimeException {

    public NutritionServiceException(String message) {
        super(message);
    }
}
