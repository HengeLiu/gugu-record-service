package com.nutrition.nutritionservice.biz;

import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.controller.ao.NutrientWeightAo;
import com.nutrition.nutritionservice.converter.NutrientWeightVo2AoConverter;
import com.nutrition.nutritionservice.service.UserNutrientWeightSumDailyService;
import com.nutrition.nutritionservice.vo.UserNutrientWeightSumDailyVo;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * 用户营养素摄入重量每日汇总
 *
 * @author heng.liu
 * @since 2021/1/7
 */
@Biz
public class UserNutrientWeightSumDailyBiz {

    @Resource
    private UserNutrientWeightSumDailyService userNutrientWeightSumDailyService;



    public List<NutrientWeightAo> queryUserNutrientWeightSumDaily(String uuid, Double targetCalorie,
                                                                  LocalDate date) {
        List<UserNutrientWeightSumDailyVo> userNutrientWeightSumDailyVoList = userNutrientWeightSumDailyService
                .queryByUuidAndDate(uuid, date);
        return NutrientWeightVo2AoConverter.convert(targetCalorie, userNutrientWeightSumDailyVoList);
    }
}
