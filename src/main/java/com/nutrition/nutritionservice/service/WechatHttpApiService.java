package com.nutrition.nutritionservice.service;

import javafx.beans.binding.ObjectExpression;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.net.www.http.HttpClient;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 微信接口服务。
 *
 * @author heng.liu
 * @since 2020/9/23
 */
@Service
@Slf4j
public class WechatHttpApiService {

    @Value("${wechat.miniProgram.appId}")
    private String appId;
    @Value("${wechat.miniProgram.appSecret}")
    private String appSecret;

    private final String code2SessionUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret="
            + appSecret + "&grant_type=authorization_code&js_code=";

    public Object getUserSession(String jsCode) {
        HttpGet httpGet = new HttpGet(code2SessionUrl + jsCode);
        try (CloseableHttpResponse response = HttpClients.createDefault().execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity responseEntity = response.getEntity();
        } catch (Exception e) {
            log.error("查询微信用户会话时发生异常", e);
        }
        return null;
    }

}
