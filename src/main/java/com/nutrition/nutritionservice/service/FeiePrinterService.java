package com.nutrition.nutritionservice.service;

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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 飞鹅打印机服务
 *
 * @author heng.liu
 * @since 2021/4/30
 */
@Service
@Slf4j
public class FeiePrinterService {

    @Resource
    private ConfigPropertiesService configPropertiesService;

    public void printCustomCuisineQRLabel(String customCuisineCode, String orderId) {
        String content = "";
        content += "<DIRECTION>1</DIRECTION>";
        content += "<TEXT x='10' y='10' font='12' w='1' h='1' r='0'>";
        content += "自定义套餐";
        content += "</TEXT>";
        content += "<TEXT x='80' y='40' font='12' w='2' h='2' r='0'>";
        content += orderId;
        content += "</TEXT>";

        content += "<QR x='10'  y='100'  e='L'  w='25'>";
        content += configPropertiesService.getMiniProgramCuisineDetailsQrUrl() + customCuisineCode;
        content += "</QR>";

        content += "<TEXT x='10' y='220' font='12' w='1' h='1' r='0'>";
        content += "微信扫码";
        content += "</TEXT>";
        content += "<TEXT x='10' y='220' font='12' w='1' h='1' r='0'>";
        content += "饮食健康，不再迷茫";
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
