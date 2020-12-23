package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientIntakesVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelIngredientIntakesDao {

    ModelIngredientIntakesVo selectByCalorieGoal(int calorie, int goal);

    List<ModelIngredientIntakesVo> selectAll();

}