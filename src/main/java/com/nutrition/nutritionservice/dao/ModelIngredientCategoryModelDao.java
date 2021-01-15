package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientCategoryModelVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelIngredientCategoryModelDao {

    ModelIngredientCategoryModelVo selectByCalorieGoal(double standardCalorie, int goal);

    List<ModelIngredientCategoryModelVo> selectAll();

}