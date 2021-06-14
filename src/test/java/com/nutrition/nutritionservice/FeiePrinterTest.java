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
    public void testPrintLabelMsg() {
        String content = "";
        content += "<DIRECTION>1</DIRECTION>";
        content += "<TEXT x='10' y='15' font='12' w='1' h='1' r='0'>";
        content += "自定义套餐";
        content += "</TEXT>";
        content += "<TEXT x='10' y='40' font='12' w='2' h='2' r='0'>";
        content += "美团#91";
        content += "</TEXT>";

        content += "<QR x='10'  y='100'  e='L'  w='5'>";
        content += configPropertiesService.getMiniProgramCuisineDetailsQrUrl() + "customCuisineCodecustomCuisineCode";
        content += "</QR>";

        content += "<TEXT x='50' y='310' font='12' w='1' h='1' r='0'>";
        content += "微信扫码";
        content += "</TEXT>";
        content += "<TEXT x='270' y='130' font='12' w='2' h='1' r='0'>";
        content += "光吃不记";
        content += "</TEXT>";
        content += "<TEXT x='270' y='220' font='12' w='2' h='1' r='0'>";
        content += "假把式";
        content += "</TEXT>";
        this.printLabelMsg(content, 1);
    }

    @Test
    public void printLabelMsg() {
        String content = "";
        content += "<DIRECTION>1</DIRECTION>";
        content += "<TEXT x='10' y='40' font='12' w='2' h='2' r='0'>";
        content += "青柠鸡胸肉";
        content += "</TEXT>";
        content += "<TEXT x='10' y='100' font='12' w='2' h='2' r='0'>";
        content += "荞麦面";
        content += "</TEXT>";
        content += "<TEXT x='10' y='160' font='12' w='2' h='2' r='0'>";
        content += "标准份量";
        content += "</TEXT>";
        this.printLabelMsg(content, 5);
    }

    @Test
    public void printCuisineLabelMsg() {
        String content = "";
        content += "<DIRECTION>1</DIRECTION>";
        content += "<TEXT x='20' y='10' font='12' w='2' h='2' r='0'>";
        content += "嫩煎鸡腿肉";
        content += "</TEXT>";
        content += "<TEXT x='20' y='60' font='12' w='2' h='2' r='0'>";
        content += "花椰菜沙拉";
        content += "</TEXT>";
        content += "<TEXT x='20' y='130' font='12' w='1' h='1' r='0'>";
        content += "总热量: 281 千卡";
        content += "</TEXT>";
        content += "<TEXT x='20' y='160' font='12' w='1' h='1' r='0'>";
        content += "其中肉类热量: 167 千卡";
        content += "</TEXT>";
        content += "<TEXT x='20' y='200' font='12' w='1' h='1' r='0'>";
        content += "蛋白质: 100 克";
        content += "</TEXT>";
        this.printLabelMsg(content, 5);
    }

    @Test
    public void printRiceLabelMsg() {
        String content = "";
        content += "<DIRECTION>1</DIRECTION>";
        content += "<TEXT x='20' y='10' font='12' w='2' h='2' r='0'>";
        content += "荞麦面主食碗";
        content += "</TEXT>";
        content += "<TEXT x='20' y='60' font='12' w='2' h='2' r='0'>";
        content += "女生均衡";
        content += "</TEXT>";
        content += "<TEXT x='20' y='130' font='12' w='1' h='1' r='0'>";
        content += "总热量: 340 千卡";
        content += "</TEXT>";
        content += "<TEXT x='20' y='160' font='12' w='1' h='1' r='0'>";
        content += "其中主食热量: 300 千卡";
        content += "</TEXT>";
        content += "<TEXT x='20' y='200' font='12' w='1' h='1' r='0'>";
        content += "蛋白质: 0 克";
        content += "</TEXT>";
        this.printLabelMsg(content, 20);
    }

    private void printLabelMsg(String content, int times) {

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
        bodyEntity.add(new BasicNameValuePair("times", "" + times));// 打印联数

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
