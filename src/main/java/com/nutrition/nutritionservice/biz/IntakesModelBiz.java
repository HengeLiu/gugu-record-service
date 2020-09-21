package com.nutrition.nutritionservice.biz;

import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import com.nutrition.nutritionservice.service.MetabolismLevelService;
import com.nutrition.nutritionservice.vo.IntakesModelUserInfoParamVo;
import com.nutrition.nutritionservice.vo.IntakesModelVo;
import com.nutrition.nutritionservice.vo.modeldata.ModelMetabolismLevelVo;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
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
    private MetabolismLevelService metabolismLevelService;

    @Resource
    private ConfigPropertiesService configPropertiesService;

    public IntakesModelVo calculateIntakesModel(IntakesModelUserInfoParamVo param) {
        ModelMetabolismLevelVo modelMetabolismLevelVo = metabolismLevelService.selectByGenderAndAge(param.getGender(),
                param.getAge());
        Map<String, Double> sportLevelValueMap = configPropertiesService.sportLevelValueMap();
        double dailyCalorie = modelMetabolismLevelVo.getMetabolismLevel() * param.getWeight()
                * sportLevelValueMap.get(String.valueOf(param.getSportLevel()));
        
        return null;
    }

}
