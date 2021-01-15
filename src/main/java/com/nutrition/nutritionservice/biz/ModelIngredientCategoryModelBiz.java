package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import com.nutrition.nutritionservice.service.CuisineService;
import com.nutrition.nutritionservice.service.EnergyCalorieCalculateService;
import com.nutrition.nutritionservice.service.ModelIngredientCategoryModelService;
import com.nutrition.nutritionservice.service.UserIngredientCategoryModelService;
import com.nutrition.nutritionservice.util.ModelUtil;
import com.nutrition.nutritionservice.vo.ModelParamVo;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientCategoryModelVo;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 食材分类模型
 *
 * @author heng.liu
 * @since 2021/1/7
 */
@Biz
@Slf4j
public class ModelIngredientCategoryModelBiz {

    @Resource
    private ConfigPropertiesService configPropertiesService;

    @Resource
    private EnergyCalorieCalculateService energyCalorieCalculateService;

    @Resource
    private ModelIngredientCategoryModelService modelIngredientCategoryModelService;

    @Resource
    private UserIngredientCategoryModelService userIngredientCategoryModelService;

    @Resource
    private CuisineService cuisineService;

    public ModelIngredientCategoryModelVo calculateIngredientModel(ModelParamVo paramVo) {
        double dailyCalorie = energyCalorieCalculateService.calculateCalorie(paramVo);
        return modelIngredientCategoryModelService.queryModelByCalorieGoal(dailyCalorie, paramVo.getGoal());
    }

    public ModelIngredientCategoryModelVo queryMostNeededModel() {
        List<ModelIngredientCategoryModelVo> allModelsList = modelIngredientCategoryModelService.listAllModels();
        List<ModelIngredientCategoryModelVo> mostNeededModelList = Lists.newArrayList();
        Map<String, Integer> userModelCountMap = Maps.newHashMap();
        Map<String, Integer> cuisineCountMap = Maps.newHashMap();
        double userModelCountSum = 0;
        double cuisineCountSum = 0;
        for (ModelIngredientCategoryModelVo model : allModelsList) {
            double modelCalorie = model.getStandardCalorie();
            int goal = model.getGoal();
            int userModelCount = userIngredientCategoryModelService.countActiveByCalorieAndGoal(modelCalorie, goal);
            int cuisineCount = cuisineService.countByCalorieAndGoal((int) (modelCalorie * 0.8),
                    (int) (modelCalorie * 1.2), goal);
            log.debug("model calorie {}, user model count {}, cuisine count {}.", modelCalorie, userModelCount,
                    cuisineCount);
            userModelCountMap.put(modelCalorie + "-" + goal, userModelCount);
            cuisineCountMap.put(modelCalorie + "-" + goal, cuisineCount);
            userModelCountSum += userModelCount;
            cuisineCountSum += cuisineCount;
        }
        double maxNeededNumber = Double.MIN_VALUE;
        for (ModelIngredientCategoryModelVo model : allModelsList) {
            String key = model.getStandardCalorie() + "-" + model.getGoal();
            double userModelRate = userModelCountMap.getOrDefault(key, 0) / userModelCountSum;
            double cuisineRate = cuisineCountSum == 0 ? 0.0 : cuisineCountMap.getOrDefault(key, 0) / cuisineCountSum;
            double rateMinus = userModelRate - cuisineRate;
            log.debug("model calorie {}, goal {}, user model rate {}, cuisine model rate {}, rate minus {}.",
                    model.getStandardCalorie(), model.getGoal(), userModelRate, cuisineRate, rateMinus);
            if (rateMinus > maxNeededNumber) {
                maxNeededNumber = rateMinus;
                mostNeededModelList = Lists.newArrayList();
                mostNeededModelList.add(model);
            } else if (rateMinus == maxNeededNumber) {
                mostNeededModelList.add(model);
            }
        }
        if (mostNeededModelList.size() == 1) {
            return mostNeededModelList.get(0);
        }
        ModelIngredientCategoryModelVo defaultUserModel = calculateIngredientModel(
                configPropertiesService.getDefaultUserInfo());
        if (mostNeededModelList.size() == 0) {
            log.info("no most needed model found.");
            return defaultUserModel;
        }
        log.debug("default user model calorie {}, gaol {}.", defaultUserModel.getStandardCalorie(), defaultUserModel.getGoal());
        double minEuclidDistance = Double.MAX_VALUE;
        ModelIngredientCategoryModelVo bestModel = null;
        for (ModelIngredientCategoryModelVo model : mostNeededModelList) {
            double modelEuclidDistance = ModelUtil.calculateEuclidDistance(model, defaultUserModel);
            log.debug("model calorie {}, goal {}, Euclid Distance to default model {}.", model.getStandardCalorie(),
                    model.getGoal(), modelEuclidDistance);
            if (modelEuclidDistance < minEuclidDistance) {
                minEuclidDistance = modelEuclidDistance;
                bestModel = model;
            }
        }
        return bestModel;
    }

}
