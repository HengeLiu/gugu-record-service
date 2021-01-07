package com.nutrition.nutritionservice;

import com.nutrition.nutritionservice.biz.ModelIngredientIntakesBiz;
import com.nutrition.nutritionservice.converter.Model2UserModelConverter;
import com.nutrition.nutritionservice.dao.UserInfoDao;
import com.nutrition.nutritionservice.enums.database.UserIngredientModelStatusEnum;
import com.nutrition.nutritionservice.service.EnergyCalorieCalculateService;
import com.nutrition.nutritionservice.service.ModelIngredientIntakesService;
import com.nutrition.nutritionservice.service.UserIngredientCategoryModelService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.util.ModelUtil;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientCategoryModelVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientCategoryModelVo;
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
    private UserIngredientCategoryModelService userIngredientCategoryModelService;

    @Resource
    private ModelIngredientIntakesBiz modelIngredientIntakesBiz;

    @Resource
    private UserInfoDao userInfoDao;

    @Test
    public void recalculateAllUserCalorieAndModel() {
        int uuid = 100000;
        while (++uuid <= 100102) {
            System.out.println(uuid);
            UserInfoVo userInfoVo = userInfoService.selectByUuid(String.valueOf(uuid));
            if (userInfoVo.getCalorie() == 0) {
                int calorie = energyCalorieCalculateService.calculateByUserInfo(userInfoVo);
                userInfoVo.setCalorie((double) calorie);
                userInfoService.add(userInfoVo);
            }
            ModelIngredientCategoryModelVo intakesModel = modelIngredientIntakesService
                    .getIntakesByCalorieGoal(userInfoVo.getCalorie(), userInfoVo.getGoal());
            UserIngredientCategoryModelVo userModel = Model2UserModelConverter.convert(intakesModel,
                    String.valueOf(uuid), UserIngredientModelStatusEnum.USING);
            userIngredientCategoryModelService.add(userModel);
        }
    }

    @Test
    public void calculateAllModelSimilarity() {
        List<ModelIngredientCategoryModelVo> modelList = modelIngredientIntakesService.listAllModels();
        for (ModelIngredientCategoryModelVo model1 : modelList) {
            System.out.print("" + model1.getCalorie() + "," + model1.getGoal());
            for (ModelIngredientCategoryModelVo model2 : modelList) {
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
        ModelIngredientCategoryModelVo mostNeededModel = modelIngredientIntakesBiz.queryMostNeededModel();
        System.out.println();
    }

    @Test
    public void saveModelIdToUserInfo() {
        List<UserInfoVo> userInfoList = userInfoDao.selectAll();
        for (UserInfoVo userInfoVo : userInfoList) {
            UserIngredientCategoryModelVo userIngredientCategoryModelVo = userIngredientCategoryModelService
                    .queryLastByUuid(userInfoVo.getUuid());
            if (userIngredientCategoryModelVo == null) {
                log.info("user {} has no model.", userInfoVo.getUuid());
                continue;
            }
            int userModelId = userIngredientCategoryModelVo.getId();
            userInfoVo.setActiveModelId((long) userModelId);
            userInfoService.updateSelective(userInfoVo);
        }
    }


}
