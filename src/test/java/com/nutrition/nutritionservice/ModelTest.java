package com.nutrition.nutritionservice;

import com.nutrition.nutritionservice.service.EnergyCalorieCalculateService;
import com.nutrition.nutritionservice.service.ModelIngredientIntakesService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientIntakesVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author heng.liu
 * @since 2020/12/22
 */
@SpringBootTest
public class ModelTest {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private EnergyCalorieCalculateService energyCalorieCalculateService;

    @Resource
    private ModelIngredientIntakesService modelIngredientIntakesService;

    @Test
    public void recalculateAllUserCalorieAndModel() {
        int uuid = 100000;
        while (++uuid <= 100102) {
            UserInfoVo userInfoVo = userInfoService.selectByUuid(String.valueOf(uuid));
            int calorie = energyCalorieCalculateService.calculateByUserInfo(userInfoVo);
            userInfoVo.setCalorie(calorie);
            userInfoService.saveUserInfo(userInfoVo);
            ModelIngredientIntakesVo intakesByCalorieGoal = modelIngredientIntakesService
                    .getIntakesByCalorieGoal(calorie, userInfoVo.getGoal());

        }

    }

}
