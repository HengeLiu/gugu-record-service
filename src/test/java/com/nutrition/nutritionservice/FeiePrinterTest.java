package com.nutrition.nutritionservice;

import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 飞鹅打印机测试
 *
 * @author heng.liu
 * @since 2021/4/28
 */
@SpringBootTest
public class FeiePrinterTest {

    @Resource
    private ConfigPropertiesService configPropertiesService;

    @Test
    public void printTimeStamp() {
        long timestamp = new Date().getTime();
        String feieUser = configPropertiesService.getFeieUser();
        String feieUkey = configPropertiesService.getFeieUkey();
        String sig = DigestUtils.sha1Hex(feieUser + feieUkey + timestamp);
        System.out.println(timestamp);
        System.out.println(feieUser);
        System.out.println(feieUkey);
        System.out.println(sig);
    }

}
