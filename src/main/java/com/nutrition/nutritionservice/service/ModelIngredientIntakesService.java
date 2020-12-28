package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.ModelIngredientIntakesDao;
import com.nutrition.nutritionservice.enums.database.ModelGoalEnum;
import com.nutrition.nutritionservice.vo.modeldata.IntakesModelVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author heng.liu
 * @since 2020/12/19
 */
@Service
public class ModelIngredientIntakesService {

    @Resource
    private ModelIngredientIntakesDao modelIngredientIntakesDao;

    public IntakesModelVo getIntakesByCalorieGoal(int calorie, int goal) {
        int calorieTmp = (calorie / 100) * 100;
        if ((goal == ModelGoalEnum.BALANCE.getCode() || goal == ModelGoalEnum.INCREASED_MUSCLE.getCode())
                && calorieTmp > 3000) {
            return modelIngredientIntakesDao.selectByCalorieGoal(3000, goal);
        }
        if (goal == ModelGoalEnum.LOSE_WEIGHT.getCode() && calorie > 3800) {
            return modelIngredientIntakesDao.selectByCalorieGoal(3800, goal);
        }
        return modelIngredientIntakesDao.selectByCalorieGoal(calorieTmp, goal);
    }

    public List<IntakesModelVo> listAllModels() {
        return modelIngredientIntakesDao.selectAll();
    }

}
