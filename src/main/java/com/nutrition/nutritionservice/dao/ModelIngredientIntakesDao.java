package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientIntakesVo;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelIngredientIntakesDao {

    ModelIngredientIntakesVo selectByCalorieGoal(int calorie, int goal);

}