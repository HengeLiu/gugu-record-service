package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.DineRecommendedRateVo;

public interface DineRecommendedRateDao {

    DineRecommendedRateVo selectByCalorieGoalDine(double calorie, int goal, int dineTime);

}