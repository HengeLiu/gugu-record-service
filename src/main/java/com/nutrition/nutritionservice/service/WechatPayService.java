package com.nutrition.nutritionservice.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 微信支付服务
 *
 * @author heng.liu
 * @since 2021/4/30
 */
@Service
@Slf4j
public class WechatPayService {

    @Resource
    private ConfigPropertiesService configPropertiesService;

    public void createPaymentOrder(String openId) {

    }

    public void calculateRequestSign(HttpUriRequest httpUriRequest, String bodyJson) {

    }

}
