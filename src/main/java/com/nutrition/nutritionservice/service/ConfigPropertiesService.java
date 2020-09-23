package com.nutrition.nutritionservice.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 配置项服务。
 *
 * @author heng.liu
 * @since 2020/9/21
 */
@Service
@PropertySource({"classpath:business-config.properties","classpath:system-config.properties"})
public class ConfigPropertiesService {

    @Value("${business.sportLevelValueJson}")
    private String sportLevelValueJson;

    public Map<String, Double> sportLevelValueMap() {
        return JSON.parseObject(sportLevelValueJson, new TypeReference<Map<String, Double>>() {
        });
    }

}
