package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 配送
 *
 * @author heng.liu
 * @since 2021/4/27
 */
@Slf4j
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @RequestMapping("/order/change")
    public Response orderChange(HttpServletRequest request, @RequestBody Object param) {
        String requestURI = request.getRequestURI();
        return Response.success();
    }

    @RequestMapping("/order/finish")
    public Response orderFinish() {

        return Response.success();
    }

    @RequestMapping("/order/cancel")
    public Response orderCancel() {

        return Response.success();
    }

    @RequestMapping("/order/error")
    public Response orderError() {

        return Response.success();
    }

}
