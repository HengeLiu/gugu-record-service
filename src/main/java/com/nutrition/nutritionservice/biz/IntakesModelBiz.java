package com.nutrition.nutritionservice.biz;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.service.MetabolismLevelService;
import com.nutrition.nutritionservice.vo.IntakesModelUserInfoParamVo;
import com.nutrition.nutritionservice.vo.IntakesModelVo;
import com.nutrition.nutritionservice.vo.MetabolismLevelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

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

    private final Map<Integer, Double> sportLevelValueMap = Maps.newHashMap();

    {
        sportLevelValueMap.put(1, 1.5);
        sportLevelValueMap.put(2, 1.75);
        sportLevelValueMap.put(3, 2.0);
    }

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
