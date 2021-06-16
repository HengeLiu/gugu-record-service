package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.util.DateTimeUtil;
import eleme.openapi.sdk.api.entity.order.OOrder;
import eleme.openapi.sdk.api.entity.order.OrderList;
import eleme.openapi.sdk.api.exception.ServiceException;
import eleme.openapi.sdk.api.service.OrderService;
import eleme.openapi.sdk.config.Config;
import eleme.openapi.sdk.oauth.OAuthClient;
import eleme.openapi.sdk.oauth.response.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * 饿了么管理服务
 *
 * @author heng.liu
 * @since 2021/6/16
 */
@Service
@Slf4j
public class ElemeManageService {

    // 变量为true: 沙箱环境 false: 生产环境
    private final boolean isSandbox = true;
    // 当前环境key
    // private final String appKey = "ubuNUyhTvm";
    private final String appKey = "TI1HIijkrO";

    // 当前环境secret
    // private final String appSecret = "306bd36d3ac08c23b3bd352b0657ed0dfbf14cfd";
    private final String appSecret = "1f10e60e0d6a19c5b73fbe89f6a79cb3a6fca1a1";

    // 实例化一个配置类
    private final Config config = new Config(isSandbox, appKey, appSecret);

    // 使用config对象，实例化一个授权类
    private final OAuthClient client = new OAuthClient(config);

    // 使用授权类获取token
    private final Token token = client.getTokenInClientCredentials();

    private final OrderService orderService = new OrderService(config, token);

    public void prepareAll(long shopId, LocalDate date) {
        String dayStr = DateTimeUtil.YMD.format(date);
        try {
            for (int pageNo = 1;; pageNo++) {
                OrderList orderList = orderService.getAllOrders(shopId, pageNo, 20, dayStr);
                if (orderList.getTotal() <= 0) {
                    break;
                }
                for (OOrder order : orderList.getList()) {
                    log.debug("ELEME order, day: {}, sn: {}", dayStr, order.getDaySn());
//                    orderService.startDeliveryBySelf(order.getId(), "");
                    orderService.receivedOrderLite(order.getId());
                }
            }
            log.info("Set all ELEME orders prepared successfully, shop id: " + shopId);
        } catch (ServiceException e) {
            log.error("Failed to set ELEME orders prepared!!! Shop id: {}", shopId, e);
        }
    }
}
