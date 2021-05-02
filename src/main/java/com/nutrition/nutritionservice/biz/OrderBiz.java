package com.nutrition.nutritionservice.biz;

import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.service.WechatPayService;

import javax.annotation.Resource;

/**
 * 点餐业务
 *
 * @author heng.liu
 * @since 2021/4/30
 */
@Biz
public class OrderBiz {

    @Resource
    private WechatPayService wechatPayService;

    public void settleAmount() {

    }


}
