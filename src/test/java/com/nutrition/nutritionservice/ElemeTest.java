package com.nutrition.nutritionservice;

import eleme.openapi.sdk.api.entity.order.OOrder;
import eleme.openapi.sdk.api.entity.order.OrderList;
import eleme.openapi.sdk.api.enumeration.order.OOrderStatus;
import eleme.openapi.sdk.api.exception.ServiceException;
import eleme.openapi.sdk.api.service.OrderService;
import eleme.openapi.sdk.api.service.ShopService;
import eleme.openapi.sdk.config.Config;
import eleme.openapi.sdk.oauth.OAuthClient;
import eleme.openapi.sdk.oauth.response.Token;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 饿了么测试
 *
 * @author heng.liu
 * @since 2021/6/16
 */
@SpringBootTest
public class ElemeTest {

    // 变量为true: 沙箱环境 false: 生产环境
    boolean isSandbox = true;
    // 当前环境key
    String appKey = "TI1HIijkrO";

    // 当前环境secret
    String appSecret = "1f10e60e0d6a19c5b73fbe89f6a79cb3a6fca1a1";

    // 实例化一个配置类
    Config config = new Config(isSandbox, appKey, appSecret);

    // 使用config对象，实例化一个授权类
    OAuthClient client = new OAuthClient(config);

    // 使用授权类获取token
    Token token = client.getTokenInClientCredentials();

    public void testShopService() {
        ShopService shopService = new ShopService(config, token);
    }

    @Test
    public void testOrderService() {
        OrderService orderService = new OrderService(config, token);
        try {
            OrderList orderList = orderService.getAllOrders(2096299733, 1, 20, "2021-06-16");
            for (OOrder order : orderList.getList()) {
                OOrderStatus status = order.getStatus();
                orderService.setOrderPrepared(order.getId());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}
