package com.nutrition.nutritionservice.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nutrition.nutritionservice.exception.NutritionServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

/**
 * 微信接口服务。
 *
 * @author heng.liu
 * @since 2020/9/23
 */
@Service
@Slf4j
public class WechatHttpApiService {

    private final String appId = "wx923f2dd6725185fd";
    private final String appSecret = "86aaa34906fbcc9ff33dd22407e1a2c5";
    private final String code2SessionUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret="
            + appSecret + "&grant_type=authorization_code&js_code=";

    public String getUserOpenId(String jsCode) {
        HttpGet httpGet = new HttpGet(code2SessionUrl + jsCode);
        try (CloseableHttpResponse response = HttpClients.createDefault().execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == 200) {
                HttpEntity responseEntity = response.getEntity();
                JSONObject resDataJO = JSON.parseObject(EntityUtils.toString(responseEntity));
                return resDataJO.getString("openid");
            }
        } catch (Exception e) {
            log.error("查询微信用户会话时发生异常", e);
            throw new NutritionServiceException("未查询到用户的微信账号");
        }
        return "";
    }

}
