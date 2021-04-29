package com.nutrition.nutritionservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * 微信支付测试
 *
 * @author heng.liu
 * @since 2021/4/29
 */
@SpringBootTest
@Slf4j
public class WechatPayTest {

    @Resource
    private ConfigPropertiesService configPropertiesService;

    private final String schema = "WECHATPAY2-SHA256-RSA2048";

    private String merchantId = "1608516327";
    private String merchantSerialNumber = "3805C0F90908991E64A8EC1EC67175797DCF7829";
    private String apiV3Key = "d5113334b32e4c159e595c5238daedec";
    private String appId = "wxdc94b625569ad4f3";

    private PrivateKey merchantPrivateKey;
    private AutoUpdateCertificatesVerifier verifier;
    private WechatPayHttpClientBuilder httpClientBuilder;

    @BeforeEach
    public void buildPreParam() {
        String privateKeyFilePath = ClassUtils.getDefaultClassLoader().getResource("static").getPath()
                + "/pem/apiclient_key.pem";
        try {
            // 加载私钥
            merchantPrivateKey = getPrivateKey(privateKeyFilePath);
            // 自动更新加载平台证书
            verifier = new AutoUpdateCertificatesVerifier(
                    new WechatPay2Credentials(merchantId,
                            new PrivateKeySigner(merchantSerialNumber, merchantPrivateKey)),
                    apiV3Key.getBytes(StandardCharsets.UTF_8));
            // 可以通过builder设置各种参数，来配置HttpClient
            httpClientBuilder = WechatPayHttpClientBuilder.create()
                    .withMerchant(merchantId, merchantSerialNumber, merchantPrivateKey)
                    .withValidator(new WechatPay2Validator(verifier));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void precreateOrder() throws Exception {

        // 通过WechatPayHttpClientBuilder构造的HttpClient，会自动的处理签名和验签
        try (CloseableHttpClient httpClient = httpClientBuilder.build()) {

            // 后面跟使用Apache HttpClient一样
            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Content-type", "application/json; charset=utf-8");

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectMapper objectMapper = new ObjectMapper();

            ObjectNode rootNode = objectMapper.createObjectNode();
            rootNode.put("mchid", merchantId).put("appid", appId)
                    .put("description", "Image形象店-深圳腾大-QQ公仔")
                    .put("notify_url", "https://www.weixin.qq.com/wxpay/pay.php")
                    .put("out_trade_no", "1217752501201407033233368018");
            rootNode.putObject("amount").put("total", 1);
            rootNode.putObject("payer").put("openid", "oR7DQ4sgReC7wxA_xp5tJtjTyiSg");

            objectMapper.writeValue(bos, rootNode);

            httpPost.setEntity(new StringEntity(bos.toString("UTF-8")));
            CloseableHttpResponse response = httpClient.execute(httpPost);

            String bodyAsString = EntityUtils.toString(response.getEntity());
            System.out.println(bodyAsString);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取私钥。
     *
     * @param filename 私钥文件路径 (required)
     * @return 私钥对象
     */
    private PrivateKey getPrivateKey(String filename) throws IOException {

        String content = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
        try {
            String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "").replaceAll("\\s+", "");

            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持RSA", e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的密钥格式");
        }
    }

    String getToken(String method, HttpUrl url, String body) {
        String nonceStr = "your nonce string";
        long timestamp = System.currentTimeMillis() / 1000;
        String message = buildMessage(method, url, timestamp, nonceStr, body);
        String signature = null;
        try {
            signature = sign(message.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        return "mchid=\"" + merchantId + "\"," + "nonce_str=\"" + nonceStr + "\"," + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + merchantSerialNumber + "\"," + "signature=\"" + signature + "\"";
    }

    String sign(byte[] message) throws NoSuchAlgorithmException, IOException, InvalidKeyException, SignatureException {
        Signature sign = Signature.getInstance("SHA256withRSA");
        String privateKeyFilePath = ClassUtils.getDefaultClassLoader().getResource("static").getPath()
                + "/pem/apiclient_key.pem";
        log.info("privateKeyFilePath\n" + privateKeyFilePath);
        sign.initSign(getPrivateKey(privateKeyFilePath));
        sign.update(message);

        return Base64.getEncoder().encodeToString(sign.sign());
    }

    String buildMessage(String method, HttpUrl url, long timestamp, String nonceStr, String body) {
        String canonicalUrl = url.encodedPath();
        if (url.encodedQuery() != null) {
            canonicalUrl += "?" + url.encodedQuery();
        }

        return method + "\n" + canonicalUrl + "\n" + timestamp + "\n" + nonceStr + "\n" + body + "\n";
    }

}
