package com.nutrition.nutritionservice;

import com.nutrition.nutritionservice.converter.IntakesModel2UserModelConverter;
import com.nutrition.nutritionservice.service.EnergyCalorieCalculateService;
import com.nutrition.nutritionservice.service.ModelIngredientIntakesService;
import com.nutrition.nutritionservice.service.UserCategoryIntakesModelService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientIntakesVo;
import com.nutrition.nutritionservice.vo.user.UserCategoryIntakesModelVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author heng.liu
 * @since 2020/12/22
 */
@SpringBootTest
@Slf4j
public class ModelTest {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private EnergyCalorieCalculateService energyCalorieCalculateService;

    @Resource
    private ModelIngredientIntakesService modelIngredientIntakesService;

    @Resource
    private UserCategoryIntakesModelService userCategoryIntakesModelService;

    @Test
    public void recalculateAllUserCalorieAndModel() {
        int uuid = 100000;
        while (++uuid <= 100102) {
            System.out.println(uuid);
            UserInfoVo userInfoVo = userInfoService.selectByUuid(String.valueOf(uuid));
            if (userInfoVo.getCalorie() == 0) {
                int calorie = energyCalorieCalculateService.calculateByUserInfo(userInfoVo);
                userInfoVo.setCalorie(calorie);
                userInfoService.saveUserInfo(userInfoVo);
            }
            ModelIngredientIntakesVo intakesModel = modelIngredientIntakesService
                    .getIntakesByCalorieGoal(userInfoVo.getCalorie(), userInfoVo.getGoal());
            UserCategoryIntakesModelVo userModel = IntakesModel2UserModelConverter.INSTANCE.convert(intakesModel);
            userModel.setUuid(String.valueOf(uuid));
            userCategoryIntakesModelService.saveUserModel(userModel);
        }

    }

}
