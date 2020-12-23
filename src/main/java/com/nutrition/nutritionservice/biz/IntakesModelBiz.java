package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Lists;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.enums.database.GenderEnum;
import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import com.nutrition.nutritionservice.service.CuisineService;
import com.nutrition.nutritionservice.service.EnergyCalorieCalculateService;
import com.nutrition.nutritionservice.service.ModelIngredientIntakesService;
import com.nutrition.nutritionservice.service.UserCategoryIntakesModelService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.vo.ModelParamVo;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientIntakesVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;

import javax.annotation.Resource;
import java.util.List;

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

    public ModelIngredientIntakesVo calculateIntakesModel(ModelParamVo paramVo) {
        int dailyCalorie = energyCalorieCalculateService.calculate(paramVo);
        return modelIngredientIntakesService.getIntakesByCalorieGoal(dailyCalorie, paramVo.getGoal());
    }

    public ModelIngredientIntakesVo getDefaultUserModel() {
        UserInfoVo defaultUserInfo = configPropertiesService.getDefaultUserInfo(GenderEnum.FEMALE);
        int dailyCalorie = energyCalorieCalculateService.calculateByUserInfo(defaultUserInfo);
        return modelIngredientIntakesService.getIntakesByCalorieGoal(dailyCalorie, defaultUserInfo.getGoal());
    }

    public ModelIngredientIntakesVo calculateIntakesModelByUuid(String uuid) {
        UserInfoVo userInfoVo = userInfoService.selectByUuid(uuid);
        if (userInfoVo.getCalorie() <= 0) {
            throw new RuntimeException("user calorie's zero");
        }
        return modelIngredientIntakesService.getIntakesByCalorieGoal(userInfoVo.getCalorie(), userInfoVo.getGoal());
    }

    public ModelIngredientIntakesVo queryMostNeededModel() {
        List<ModelIngredientIntakesVo> allModelsList = modelIngredientIntakesService.listAllModels();
        int maxNeededNumber = Integer.MIN_VALUE;
        List<ModelIngredientIntakesVo> mostNeededModelList = Lists.newArrayList();
        for (ModelIngredientIntakesVo model : allModelsList) {
            int modelCalorie = model.getCalorie();
            int goal = model.getGoal();
            int userModelCount = userCategoryIntakesModelService.countByCalorieAndGoal(modelCalorie, goal);
            int cuisineCount = cuisineService.countByCalorieAndGoal((int) (modelCalorie * 0.8),
                    (int) (modelCalorie * 1.2), goal);
            log.debug("model calorie {}, user model count {}, cuisine count {}.", modelCalorie, userModelCount,
                    cuisineCount);
            if (userModelCount - cuisineCount > maxNeededNumber) {
                maxNeededNumber = userModelCount - cuisineCount;
                mostNeededModelList = Lists.newArrayList();
                mostNeededModelList.add(model);
            } else if (userModelCount - cuisineCount == maxNeededNumber) {
                mostNeededModelList.add(model);
            }
        }
        if (mostNeededModelList.size() == 1) {
            return mostNeededModelList.get(0);
        }
        ModelIngredientIntakesVo defaultUserModel = getDefaultUserModel();
        if (mostNeededModelList.size() == 0) {
            return defaultUserModel;
        }
        double minEuclidDistance = Double.MAX_VALUE;
        ModelIngredientIntakesVo bestModel = null;
        for (ModelIngredientIntakesVo model : mostNeededModelList) {
            double modelEuclidDistance = modelIngredientIntakesService.calculateEuclidDistance(model, defaultUserModel);
            if (modelEuclidDistance < minEuclidDistance) {
                minEuclidDistance = modelEuclidDistance;
                bestModel = model;
            }
        }
        return bestModel;
    }

}
