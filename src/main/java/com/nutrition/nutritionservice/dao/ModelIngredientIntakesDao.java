package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.modeldata.IntakesModelVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelIngredientIntakesDao {

    IntakesModelVo selectByCalorieGoal(double calorie, int goal);

    List<IntakesModelVo> selectAll();

}