package com.nutrition.nutritionservice.biz;

import com.nutrition.nutritionservice.annotation.Biz;
import eleme.openapi.ws.sdk.config.BusinessHandle;
import lombok.extern.slf4j.Slf4j;

/**
 * 饿了么业务处理实现
 *
 * @author heng.liu
 * @since 2021/6/16
 */
@Biz
@Slf4j
public class ElemeBusinessHandle implements BusinessHandle {
    @Override
    public boolean onMessage(String s) {
//        log.info("eleme message: " + s);
        return true;
    }
}
