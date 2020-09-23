package com.nutrition.nutritionservice.biz;

import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.service.WechatHttpApiService;

import javax.annotation.Resource;

/**
 * 用户业务层。
 *
 * @author heng.liu
 * @since 2020/9/21
 */
@Biz
public class UserBiz {

    @Resource
    private WechatHttpApiService wechatHttpApiService;

    public String loginWithWechat(String jsCode) {
        Object userSession = wechatHttpApiService.getUserSession(jsCode);
        return "";
    }

}
