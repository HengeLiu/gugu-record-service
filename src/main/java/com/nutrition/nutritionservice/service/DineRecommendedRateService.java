package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.DineRecommendedRateDao;
import com.nutrition.nutritionservice.vo.DineRecommendedRateVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 每餐推荐比例 Service。
 * 
 * @author heng.liu
 * @since 2020/12/25
 */
@Service
public class DineRecommendedRateService {

    @Resource
    private DineRecommendedRateDao dineRecommendedRateDao;

    public DineRecommendedRateVo selectByCalorieGoalDine(int calorie, int goal, int dineTime) {
        return dineRecommendedRateDao.selectByCalorieGoalDine(calorie, goal, dineTime);
    }

}
