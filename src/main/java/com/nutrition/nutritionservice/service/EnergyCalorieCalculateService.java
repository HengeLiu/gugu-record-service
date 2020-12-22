package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.enums.CodeEnums;
import com.nutrition.nutritionservice.enums.ProfeCharEnum;
import com.nutrition.nutritionservice.enums.SportHabitEnum;
import com.nutrition.nutritionservice.dao.ModelBasicMetabolicRateDao;
import com.nutrition.nutritionservice.vo.ModelParamVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 热量计算服务。
 *
 * @author heng.liu
 * @since 2020/12/18
 */
@Service
public class EnergyCalorieCalculateService {

    @Resource
    private ModelBasicMetabolicRateDao modelBasicMetabolicRateDao;

    public int calculate(ModelParamVo modelBaseInfoVo) {
        double surfaceArea = 0.006 * modelBaseInfoVo.getHeight() + 0.0126 * modelBaseInfoVo.getWeight() - 0.1603;
        double bmr;
        if (modelBaseInfoVo.getAge() > 45) {
            bmr = modelBasicMetabolicRateDao.selectBmrByAgeGender(45, modelBaseInfoVo.getGender());
        } else {
            bmr = modelBasicMetabolicRateDao.selectBmrByAgeGender(modelBaseInfoVo.getAge(),
                    modelBaseInfoVo.getGender());
        }

        double sportLevelRatio = sportLevelRatio(CodeEnums.valueOf(ProfeCharEnum.class, modelBaseInfoVo.getProfeChar()),
                CodeEnums.valueOf(SportHabitEnum.class, modelBaseInfoVo.getSportHabits()));
        double bee = surfaceArea * bmr * 24 * sportLevelRatio;
        return (int) bee;
    }

    public int calculateByUserInfo(UserInfoVo userInfoVo) {
        ModelParamVo modelParamVo = ModelParamVo.builder().goal(userInfoVo.getGoal()).age(userInfoVo.getAge())
                .gender(userInfoVo.getGender()).height(userInfoVo.getHeight()).weight(userInfoVo.getWeight())
                .profeChar(userInfoVo.getProfeChar()).sportHabits(userInfoVo.getSportsHabits()).build();
        return calculate(modelParamVo);
    }

    private double sportLevelRatio(ProfeCharEnum profeCharEnum, SportHabitEnum sportHabitEnum) {
        if (profeCharEnum == null || sportHabitEnum == null) {
            return 0.0;
        }
        return 1.5 + 0.5 * ((double) (profeCharEnum.getScore() + sportHabitEnum.getScore()) / 6);
    }

}
