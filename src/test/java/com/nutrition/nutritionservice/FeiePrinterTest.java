package com.nutrition.nutritionservice;

import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 飞鹅打印机测试
 *
 * @author heng.liu
 * @since 2021/4/28
 */
@Slf4j
@SpringBootTest
public class FeiePrinterTest {

    @Resource
    private ConfigPropertiesService configPropertiesService;

    @Test
    public void printLabelMsg() {

        String content = "";
        content += "<DIRECTION>1</DIRECTION>";
        content += "<TEXT x='10' y='10' font='12' w='1' h='1' r='0'>";
        content += "测试单子";
        content += "</TEXT>";
        content += "<TEXT x='80' y='40' font='12' w='2' h='2' r='0'>";
        content += "标题";
        content += "</TEXT>";

        content += "<QR x='10'  y='100'  e='L'  w='20'>这是一个二维码</QR>";
        content += "<LOGO x='150' y='100'>";

        content += "<TEXT x='10' y='220' font='12' w='1' h='1' r='0'>";
        content += "单号：****************";
        content += "</TEXT>";

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();

        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

        HttpPost httpPost = new HttpPost(configPropertiesService.getFeieUrl());
        List<NameValuePair> bodyEntity = new ArrayList<>();

        long timestamp = System.currentTimeMillis();
        String feieUser = configPropertiesService.getFeieUser();
        String feieUkey = configPropertiesService.getFeieUkey();
        String sn = configPropertiesService.getSummerLongLabelPrinterSn();
        String stime = String.valueOf(timestamp / 1000);
        String sig = DigestUtils.sha1Hex(feieUser + feieUkey + stime);

        bodyEntity.add(new BasicNameValuePair("user", configPropertiesService.getFeieUser()));
        bodyEntity.add(new BasicNameValuePair("stime", stime));
        bodyEntity.add(new BasicNameValuePair("sig", sig));
        bodyEntity.add(new BasicNameValuePair("apiname", "Open_printLabelMsg"));// 固定值,不需要修改
        bodyEntity.add(new BasicNameValuePair("sn", sn));
        bodyEntity.add(new BasicNameValuePair("content", content));
        bodyEntity.add(new BasicNameValuePair("times", "1"));// 打印联数

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(bodyEntity, "utf-8"));
            String responseBody = httpClient.execute(httpPost, httpResponse -> {
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status < 200 || status >= 300) {
                    log.error("请求异常");
                }
                HttpEntity entity = httpResponse.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            });
            log.info(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
