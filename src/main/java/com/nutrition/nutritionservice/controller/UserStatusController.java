package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.UserStatusInfoBiz;
import com.nutrition.nutritionservice.common.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户状态
 *
 * @author heng.liu
 * @since 2021/2/25
 */
@RestController
@RequestMapping("/user-status")
public class UserStatusController {

    @Resource
    private UserStatusInfoBiz userStatusInfoBiz;

    @GetMapping("/not-notify-order-helper")
    public Response notNotifyOrderHelper(String uuid) {
        userStatusInfoBiz.notNotifyOrderHelper(uuid);
        return Response.success();
    }

    @GetMapping("/query-by-uuid")
    public Response query(String uuid) {
        return Response.success(userStatusInfoBiz.query(uuid));
    }

}
