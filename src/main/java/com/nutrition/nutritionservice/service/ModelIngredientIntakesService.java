package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.enums.ModelGoalEnum;
import com.nutrition.nutritionservice.mapper.ModelIngredientIntakesMapper;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientIntakesVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author heng.liu
 * @since 2020/12/19
 */
@Service
public class ModelIngredientIntakesService {

    @Resource
    private ModelIngredientIntakesMapper modelIngredientIntakesMapper;

    public ModelIngredientIntakesVo getIntakesByCalorieGoal(int calorie, int goal) {
        int calorieTmp = (calorie / 100) * 100;
        if ((goal == ModelGoalEnum.BALANCE.getCode() || goal == ModelGoalEnum.INCREASED_MUSCLE.getCode())
                && calorieTmp > 3000) {
            return modelIngredientIntakesMapper.selectByCalorieGoal(3000, goal);
        }
        if (goal == ModelGoalEnum.LOSE_WEIGHT.getCode() && calorie > 3800) {
            return modelIngredientIntakesMapper.selectByCalorieGoal(3800, goal);
        }
        return modelIngredientIntakesMapper.selectByCalorieGoal(calorieTmp, goal);
    }

}
