package com.nutrition.nutritionservice.mapper;

import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientIntakesVo;

public interface ModelIngredientIntakesMapper {

    ModelIngredientIntakesVo selectByCalorieGoal(int calorie, int goal);

}