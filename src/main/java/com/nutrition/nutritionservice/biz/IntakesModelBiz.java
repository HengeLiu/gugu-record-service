package com.nutrition.nutritionservice.biz;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.service.MetabolismLevelService;
import com.nutrition.nutritionservice.vo.IntakesModelUserInfoParamVo;
import com.nutrition.nutritionservice.vo.IntakesModelVo;
import com.nutrition.nutritionservice.vo.MetabolismLevelVo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

/**
 * 摄入模型业务层。
 * 
 * @author heng.liu
 * @since 2020/9/13
 */
@Biz
@Slf4j
public class IntakesModelBiz {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private MetabolismLevelService metabolismLevelService;

    @Value("${business.sportLevelValueJson}")
    private String sportLevelValueJson;

    public MetabolismLevelVo testDatabase(int gender, int age) {
        MetabolismLevelVo metabolismLevelVo = metabolismLevelService.selectByGenderAndAge(gender, age);
        log.info(JSON.toJSONString(metabolismLevelVo));
        return metabolismLevelVo;
    }

    public IntakesModelVo calculateIntakesModel(IntakesModelUserInfoParamVo param) {
        String querySql = "select id,age,gender,metabolism_level from metabolism_level where gender = "
                + param.getGender() + " and age = " + param.getAge() + " order by age limit 1";
        MetabolismLevelVo metabolismLevelVo = jdbcTemplate.queryForObject(querySql, MetabolismLevelVo.class);
        log.info(JSON.toJSONString(metabolismLevelVo));
        return null;
    }

}
