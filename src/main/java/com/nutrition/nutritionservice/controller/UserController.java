package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.UserBiz;
import com.nutrition.nutritionservice.vo.user.UserAccountVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/login/wechat")
    public UserAccountVo loginWechat(@RequestParam String jsCode) {
        return userBiz.loginWithWechat(jsCode);
    }

}
