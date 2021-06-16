package com.nutrition.nutritionservice.configure;

import eleme.openapi.ws.sdk.config.ElemeSdkLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 饿了么日志
 *
 * @author heng.liu
 * @since 2021/6/16
 */
@Service
@Slf4j
public class ElemeLogger implements ElemeSdkLogger {

    @Override
    public void info(String s) {
        log.info("eleme sdk info: " + s);
    }

    @Override
    public void error(String s) {
        log.error("eleme sdk error: " + s);
    }
}
