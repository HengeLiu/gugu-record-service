package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.enums.CodeEnums;
import com.nutrition.nutritionservice.enums.ProfeCharEnum;
import com.nutrition.nutritionservice.enums.SportHabitEnum;
import com.nutrition.nutritionservice.mapper.ModelBasicMetabolicRateMapper;
import com.nutrition.nutritionservice.vo.modeldata.ModelBaseInfoVo;
import com.nutrition.nutritionservice.vo.modeldata.ModelBasicMetabolicRateVo;
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
    private ModelBasicMetabolicRateMapper modelBasicMetabolicRateMapper;

    public int calculate(ModelBaseInfoVo modelBaseInfoVo) {
        double surfaceArea = 0.006 * modelBaseInfoVo.getHeight() + 0.0126 * modelBaseInfoVo.getWeight() - 0.1603;
        double bmr = modelBasicMetabolicRateMapper.selectBmrByAgeGender(modelBaseInfoVo.getAge(),
                modelBaseInfoVo.getGender());
        double sportLevelRatio = sportLevelRatio(CodeEnums.valueOf(ProfeCharEnum.class, modelBaseInfoVo.getProfeChar()),
                CodeEnums.valueOf(SportHabitEnum.class, modelBaseInfoVo.getSportHabits()));
        double bee = surfaceArea * bmr * 24 * sportLevelRatio;
        return (int) bee;
    }

    private double sportLevelRatio(ProfeCharEnum profeCharEnum, SportHabitEnum sportHabitEnum) {
        if (profeCharEnum == null || sportHabitEnum == null) {
            return 0.0;
        }
        return 1.5 + 0.5 * ((double) (profeCharEnum.getScore() + sportHabitEnum.getScore()) / 6);
    }

}
