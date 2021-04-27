package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.UserHistoricalOrderBiz;
import com.nutrition.nutritionservice.common.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户点餐历史记录相关
 *
 * @author heng.liu
 * @since 2021/2/25
 */
@Controller
@RestController
@RequestMapping("/order")
public class UserHistoricalOrderController {

    @Resource
    private UserHistoricalOrderBiz userHistoricalOrderBiz;

    @GetMapping("/save-history")
    public Response saveOrderHistory(@RequestParam String uuid, @RequestParam String cuisineCode) {
        userHistoricalOrderBiz.saveHistoricalOrder(uuid, cuisineCode);
        return Response.success();
    }

    @GetMapping("/delete-history")
    public Response deleteOrderHistory(@RequestParam Long orderHistoryId) {
        userHistoricalOrderBiz.deleteHistoricalOrder(orderHistoryId);
        return Response.success();
    }

    @GetMapping("/to-diet")
    public Response addHistoricalOrderToRecord(@RequestParam Long orderHistoryId) {
        userHistoricalOrderBiz.addHistoricalOrderToRecord(orderHistoryId);
        return Response.success();
    }

    @GetMapping("/query/details")
    public Response queryDetails(@RequestParam Long orderHistoryId) {
        return Response.success(userHistoricalOrderBiz.queryOrderRecordId(orderHistoryId));
    }

}
