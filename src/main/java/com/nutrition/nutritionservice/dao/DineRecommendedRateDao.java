package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.DineRecommendedRateVo;

public interface DineRecommendedRateDao {

    DineRecommendedRateVo selectByCalorieGoalDine(int calorie, int goal, int dine);

}