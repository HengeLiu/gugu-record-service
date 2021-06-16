package com.nutrition.nutritionservice.configure;

import com.nutrition.nutritionservice.biz.ElemeBusinessHandle;
import eleme.openapi.ws.sdk.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 饿了么启动器
 *
 * @author heng.liu
 * @since 2021/6/16
 */
@Component
@Slf4j
public class ElemeBootstrapStarter {

    @Resource
    private ElemeLogger elemeLogger;

    @Resource
    private ElemeBusinessHandle elemeBusinessHandle;

    private final List<Account> accounts = new ArrayList<>();

    public ElemeBootstrapStarter() {
        this.addAccount("TI1HIijkrO", "1f10e60e0d6a19c5b73fbe89f6a79cb3a6fca1a1");
    }

    @PostConstruct
    private void start() {
//        log.info("eleme bootstrap starting!!!!");
//        Config config = new Config(accounts, elemeBusinessHandle, elemeLogger);
//        try {
//            Bootstrap.start(config);
//            log.info("eleme bootstrap started!!!!");
//        } catch (UnableConnectionException e) {
//            log.error("failed to start eleme bootstrap!!!!", e);
//        }

    }

    @SuppressWarnings("SameParameterValue")
    private void addAccount(String appKey, String appSecret) {
        accounts.add(new Account(appKey, appSecret));
    }
}
