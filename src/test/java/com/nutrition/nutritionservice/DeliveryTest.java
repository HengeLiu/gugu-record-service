package com.nutrition.nutritionservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 配送测试
 *
 * @author heng.liu
 * @since 2021/4/28
 */
@Slf4j
@SpringBootTest
public class DeliveryTest {

    @Resource
    private ConfigPropertiesService configPropertiesService;

    @Test
    public void testPreCreateOrder() throws NoSuchAlgorithmException {
        String devId = configPropertiesService.getSfExpressDevId();
        String devKey = configPropertiesService.getSfExpressKey();
        long timestamp = System.currentTimeMillis() / 1000;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            JSONObject shopData = new JSONObject();
            shopData.put("shop_name", "夏浪轻食（日坛店）");
            shopData.put("shop_phone", "16619735232");
            shopData.put("shop_address", "神路街39号日坛上街");
            shopData.put("shop_lng", "116.443355");
            shopData.put("shop_lat", "39.918698");

            JSONObject requestBody = new JSONObject();
            requestBody.put("dev_id", devId);
            requestBody.put("shop_id", "100001");
            requestBody.put("user_lat", "39.93989");
            requestBody.put("user_lng", "116.444072");
            requestBody.put("user_address", "春秀路十一号");
            requestBody.put("city_name", "北京市");
            requestBody.put("weight", 2000);
            requestBody.put("product_type", 1);
            requestBody.put("total_price", 2850);
            requestBody.put("is_appoint", 0);
            requestBody.put("pay_type", 1);
            requestBody.put("is_insured", 0);
            requestBody.put("is_person_direct", 0);
            requestBody.put("return_flag", 511);
            requestBody.put("push_time", timestamp);
            requestBody.put("shop", shopData);

            String requestBodyStr = JSON.toJSONString(requestBody);
            String sign = sign(requestBodyStr, devId, devKey);
            String url = configPropertiesService.getSfExpressUrl() + "precreateorder?sign=" + sign;

            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(requestBodyStr, "utf-8"));
            httpPost.setHeader("Content-Type", "application/json; charset=utf-8");

            String responseBody = httpClient.execute(httpPost, httpResponse -> {
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status < 200 || status >= 300) {
                    log.error(Arrays.toString(httpResponse.getAllHeaders()));
                }
                HttpEntity entity = httpResponse.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            });

            log.info("\n++++++++++++\n" + responseBody);

        } catch (IOException e) {
            log.error("error", e);
        }

    }

    protected String sign(String contentJsonStr, String devId, String devKey) {

        String toSign = contentJsonStr + "&" + devId + "&" + devKey;

        String md5Result = md5(toSign.getBytes(StandardCharsets.UTF_8));

        return base64Encode(md5Result.getBytes(StandardCharsets.UTF_8));
    }

    protected String md5(byte[] toSignBytes) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(toSignBytes);
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected String base64Encode(byte[] md5ResultBytes) {
        java.util.Base64.Encoder be = java.util.Base64.getEncoder();
        return be.encodeToString(md5ResultBytes);
    }

    private String toHex(byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            ret.append(HEX_DIGITS[(aByte >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[aByte & 0x0f]);
        }
        return ret.toString();
    }

}
