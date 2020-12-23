package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.enums.database.GenderEnum;
import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import com.nutrition.nutritionservice.service.CuisineService;
import com.nutrition.nutritionservice.service.EnergyCalorieCalculateService;
import com.nutrition.nutritionservice.service.ModelIngredientIntakesService;
import com.nutrition.nutritionservice.service.UserCategoryIntakesModelService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.vo.ModelParamVo;
import com.nutrition.nutritionservice.vo.modeldata.IntakesModelVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 摄入模型业务层。
 * 
 * @author heng.liu
 * @since 2020/9/13
 */
@Biz
@Slf4j
public class IntakesModelBiz {

    @Resource
    private ConfigPropertiesService configPropertiesService;

    @Resource
    private EnergyCalorieCalculateService energyCalorieCalculateService;

    @Resource
    private ModelIngredientIntakesService modelIngredientIntakesService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private UserCategoryIntakesModelService userCategoryIntakesModelService;

    @Resource
    private CuisineService cuisineService;

    public IntakesModelVo calculateIntakesModel(ModelParamVo paramVo) {
        int dailyCalorie = energyCalorieCalculateService.calculate(paramVo);
        return modelIngredientIntakesService.getIntakesByCalorieGoal(dailyCalorie, paramVo.getGoal());
    }

    public IntakesModelVo getDefaultUserModel() {
        UserInfoVo defaultUserInfo = configPropertiesService.getDefaultUserInfo(GenderEnum.FEMALE);
        int dailyCalorie = energyCalorieCalculateService.calculateByUserInfo(defaultUserInfo);
        return modelIngredientIntakesService.getIntakesByCalorieGoal(dailyCalorie, defaultUserInfo.getGoal());
    }

    public IntakesModelVo calculateIntakesModelByUuid(String uuid) {
        UserInfoVo userInfoVo = userInfoService.selectByUuid(uuid);
        if (userInfoVo.getCalorie() <= 0) {
            throw new RuntimeException("user calorie's zero");
        }
        return modelIngredientIntakesService.getIntakesByCalorieGoal(userInfoVo.getCalorie(), userInfoVo.getGoal());
    }

    public IntakesModelVo queryMostNeededModel() {
        List<IntakesModelVo> allModelsList = modelIngredientIntakesService.listAllModels();
        List<IntakesModelVo> mostNeededModelList = Lists.newArrayList();
        Map<String, Integer> userModelCountMap = Maps.newHashMap();
        Map<String, Integer> cuisineCountMap = Maps.newHashMap();
        double userModelCountSum = 0;
        double cuisineCountSum = 0;
        for (IntakesModelVo model : allModelsList) {
            int modelCalorie = model.getCalorie();
            int goal = model.getGoal();
            int userModelCount = userCategoryIntakesModelService.countByCalorieAndGoal(modelCalorie, goal);
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
        for (IntakesModelVo model : allModelsList) {
            String key = model.getCalorie() + "-" + model.getGoal();
            double userModelRate = userModelCountMap.getOrDefault(key, 0) / userModelCountSum;
            double cuisineRate = cuisineCountSum == 0 ? 0.0 : cuisineCountMap.getOrDefault(key, 0) / cuisineCountSum;
            double rateMinus = userModelRate - cuisineRate;
            log.debug("model calorie {}, goal {}, user model rate {}, cuisine model rate {}, rate minus {}.",
                    model.getCalorie(), model.getGoal(), userModelRate, cuisineRate, rateMinus);
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
        IntakesModelVo defaultUserModel = getDefaultUserModel();
        if (mostNeededModelList.size() == 0) {
            log.info("no most needed model found.");
            return defaultUserModel;
        }
        log.debug("default user model calorie {}, gaol {}.", defaultUserModel.getCalorie(), defaultUserModel.getGoal());
        double minEuclidDistance = Double.MAX_VALUE;
        IntakesModelVo bestModel = null;
        for (IntakesModelVo model : mostNeededModelList) {
            double modelEuclidDistance = modelIngredientIntakesService.calculateEuclidDistance(model, defaultUserModel);
            log.debug("model calorie {}, goal {}, Euclid Distance to default model {}.", model.getCalorie(),
                    model.getGoal(), modelEuclidDistance);
            if (modelEuclidDistance < minEuclidDistance) {
                minEuclidDistance = modelEuclidDistance;
                bestModel = model;
            }
        }
        return bestModel;
    }

}
