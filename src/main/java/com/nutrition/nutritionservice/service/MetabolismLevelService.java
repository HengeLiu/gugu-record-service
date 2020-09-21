package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.mapper.MetabolismLevelMapper;
import com.nutrition.nutritionservice.vo.MetabolismLevelVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 代谢水平Service。
 * 
 * @author heng.liu
 * @since 2020/9/20
 */
@Service
public class MetabolismLevelService {

    @Resource
    private MetabolismLevelMapper metabolismLevelMapper;

    public MetabolismLevelVo selectByGenderAndAge(int gender, int age) {
        return metabolismLevelMapper.select(gender, age);
    }

}
