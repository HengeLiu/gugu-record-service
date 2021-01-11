package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.ModelIngredientCategoryModelDao;
import com.nutrition.nutritionservice.enums.database.ModelGoalEnum;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientCategoryModelVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author heng.liu
 * @since 2020/12/19
 */
@Service
public class ModelIngredientCategoryModelService {

    @Resource
    private ModelIngredientCategoryModelDao modelIngredientCategoryModelDao;

    public ModelIngredientCategoryModelVo queryModelByCalorieGoal(double calorie, int goal) {
        int calorieTmp = (int) (calorie / 100) * 100;
        if ((goal == ModelGoalEnum.BALANCE.getCode() || goal == ModelGoalEnum.INCREASED_MUSCLE.getCode())
                && calorieTmp > 3000) {
            return modelIngredientCategoryModelDao.selectByCalorieGoal(3000, goal);
        }
        if (goal == ModelGoalEnum.LOSE_WEIGHT.getCode() && calorie > 3800) {
            return modelIngredientCategoryModelDao.selectByCalorieGoal(3800, goal);
        }
        return modelIngredientCategoryModelDao.selectByCalorieGoal(calorieTmp, goal);
    }

    public List<ModelIngredientCategoryModelVo> listAllModels() {
        return modelIngredientCategoryModelDao.selectAll();
    }

}
