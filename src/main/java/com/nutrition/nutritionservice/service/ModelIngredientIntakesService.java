package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.enums.database.ModelGoalEnum;
import com.nutrition.nutritionservice.dao.ModelIngredientIntakesDao;
import com.nutrition.nutritionservice.util.VectorUtils;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientIntakesVo;
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

    public ModelIngredientIntakesVo getIntakesByCalorieGoal(int calorie, int goal) {
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

    public List<ModelIngredientIntakesVo> listAllModels() {
        return modelIngredientIntakesDao.selectAll();
    }

    public double calculateCosineSimilarity(ModelIngredientIntakesVo model1, ModelIngredientIntakesVo model2) {
        int[] v1 = modelToVector(model1);
        int[] v2 = modelToVector(model2);
        return VectorUtils.cosineSimilarity(v1, v2);
    }

    public double calculateEuclidDistance(ModelIngredientIntakesVo model1, ModelIngredientIntakesVo model2) {
        int[] v1 = modelToVector(model1);
        int[] v2 = modelToVector(model2);
        return VectorUtils.euclidDistance(v1, v2);
    }

    private int[] modelToVector(ModelIngredientIntakesVo model) {
        int[] v = new int[16];
        v[0] = model.getProcessedGrains();
        v[1] = model.getUnprocessedGrains();
        v[2] = model.getMixedBeans();
        v[3] = model.getTuber();
        v[4] = model.getGeneralVegetables();
        v[5] = model.getDarkVegetables();
        v[6] = model.getFruit();
        v[7] = model.getMeat();
        v[8] = model.getPoultry();
        v[9] = model.getAquatic();
        v[10] = model.getEgg();
        v[11] = model.getDairy();
        v[12] = model.getSoybean();
        v[13] = model.getNut();
        v[14] = model.getOil();
        v[15] = model.getSalt();
        return v;
    }

}
