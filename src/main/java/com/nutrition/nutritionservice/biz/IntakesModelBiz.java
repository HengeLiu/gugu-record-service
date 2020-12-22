package com.nutrition.nutritionservice.biz;

import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.service.EnergyCalorieCalculateService;
import com.nutrition.nutritionservice.service.ModelIngredientIntakesService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.vo.ModelParamVo;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientIntakesVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

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
    private EnergyCalorieCalculateService energyCalorieCalculateService;

    @Resource
    private ModelIngredientIntakesService modelIngredientIntakesService;

    @Resource
    private UserInfoService userInfoService;

    public ModelIngredientIntakesVo calculateIntakesModel(ModelParamVo paramVo) {
        int dailyEnergy = energyCalorieCalculateService.calculate(paramVo);
        return modelIngredientIntakesService.getIntakesByCalorieGoal(dailyEnergy, paramVo.getGoal());
    }

    public ModelIngredientIntakesVo calculateIntakesModelByUuid(String uuid) {
        UserInfoVo userInfoVo = userInfoService.selectByUuid(uuid);
        if (userInfoVo.getCalorie() <= 0) {
            throw new RuntimeException("user calorie's zero");
        }
        return modelIngredientIntakesService.getIntakesByCalorieGoal(userInfoVo.getCalorie(), userInfoVo.getGoal());
    }

}
