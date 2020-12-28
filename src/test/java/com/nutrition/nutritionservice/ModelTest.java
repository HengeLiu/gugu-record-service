package com.nutrition.nutritionservice;

import com.nutrition.nutritionservice.biz.IntakesModelBiz;
import com.nutrition.nutritionservice.converter.IntakesModel2UserModelConverter;
import com.nutrition.nutritionservice.service.EnergyCalorieCalculateService;
import com.nutrition.nutritionservice.service.ModelIngredientIntakesService;
import com.nutrition.nutritionservice.service.UserCategoryIntakesModelService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.util.ModelUtil;
import com.nutrition.nutritionservice.vo.modeldata.IntakesModelVo;
import com.nutrition.nutritionservice.vo.user.UserCategoryIntakesModelVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private IntakesModelBiz intakesModelBiz;

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
            IntakesModelVo intakesModel = modelIngredientIntakesService
                    .getIntakesByCalorieGoal(userInfoVo.getCalorie(), userInfoVo.getGoal());
            UserCategoryIntakesModelVo userModel = IntakesModel2UserModelConverter.INSTANCE.convert(intakesModel);
            userModel.setUuid(String.valueOf(uuid));
            userCategoryIntakesModelService.saveUserModel(userModel);
        }
    }

    @Test
    public void calculateAllModelSimilarity() {
        List<IntakesModelVo> modelList = modelIngredientIntakesService.listAllModels();
        for (IntakesModelVo model1 : modelList) {
            System.out.print("" + model1.getCalorie() + "," + model1.getGoal());
            for (IntakesModelVo model2 : modelList) {
                double euclidDistance = ModelUtil.calculateEuclidDistance(model1, model2);
                System.out.print("," + euclidDistance);
//                double cosineSimilarity = modelIngredientIntakesService.calculateCosineSimilarity(model1, model2);
//                System.out.print("," + cosineSimilarity);
            }
            System.out.println("");
        }
    }

    @Test
    public void testJson() {
        IntakesModelVo mostNeededModel = intakesModelBiz.queryMostNeededModel();
        System.out.println();
    }


}
