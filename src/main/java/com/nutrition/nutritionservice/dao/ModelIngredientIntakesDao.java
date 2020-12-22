package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientIntakesVo;

public interface ModelIngredientIntakesDao {

    ModelIngredientIntakesVo selectByCalorieGoal(int calorie, int goal);

}