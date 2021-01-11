package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.UserBiz;
import com.nutrition.nutritionservice.common.Response;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author heng.liu
 * @since 2020/9/13
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserBiz userBiz;

    @GetMapping("/wechat/query/userinfo")
    public Response queryUserinfo(@RequestParam String wxUserCode) {
        return Response.success(userBiz.loginWithWechat(wxUserCode));
    }

    @PostMapping("/wechat/save/userinfo")
    public Response saveUserInfo(UserInfoVo userInfoVo) {
        userBiz.saveUserInfo(userInfoVo);
        return Response.success();
    }

    @GetMapping("/save-history")
    public Response saveCuisineHistory(@RequestParam String uuid, @RequestParam String cuisineCode) {
        return Response.success(userBiz.saveCuisineHistory(uuid, cuisineCode));
    }

}
