package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.controller.ao.NutrientIntakesWeightAo;
import com.nutrition.nutritionservice.enums.CodeEnums;
import com.nutrition.nutritionservice.enums.database.NutrientEnum;
import com.nutrition.nutritionservice.service.UserNutrientWeightSumDailyService;
import com.nutrition.nutritionservice.vo.UserNutrientWeightSumDailyVo;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    /**
     * 计算用户营养素每日摄入历史重量。
     * 
     * @param uuid 用户唯一标识
     * @param date 日期
     * @param targetCalorie 所有营养素素总热量，将根据该热量处理计算误差。
     * @param nutrientCodeCalorieMap 需要展示或计算热量的营养素，key: 营养素编码，value: 营养素的热量。
     * @return 营养素摄入历史列表
     */
    public List<NutrientIntakesWeightAo> queryUserNutrientWeightSumDaily(String uuid, LocalDate date,
            Double targetCalorie, Map<Integer, Double> nutrientCodeCalorieMap) {
        List<UserNutrientWeightSumDailyVo> userNutrientWeightSumDailyVoList = userNutrientWeightSumDailyService
                .queryByUuidAndDate(uuid, date);
        if (CollectionUtils.isEmpty(userNutrientWeightSumDailyVoList)) {
            return Collections.emptyList();
        }
        List<NutrientIntakesWeightAo> nutrientWeightList = userNutrientWeightSumDailyVoList.stream()
                .filter(nutrientWeight -> nutrientCodeCalorieMap.containsKey(nutrientWeight.getNutrientCode()))
                .map(nutrientWeight -> {
                    NutrientEnum nutrientEnum = CodeEnums.valueOf(NutrientEnum.class, nutrientWeight.getNutrientCode());
                    if (nutrientEnum == null) {
                        throw new IllegalArgumentException(
                                "Nutrient not exist, code " + nutrientWeight.getNutrientCode());
                    }
                    return NutrientIntakesWeightAo.builder().nutrientCode(nutrientEnum.getCode())
                            .nutrientName(nutrientEnum.getNameZh()).weight(nutrientWeight.getWeight())
                            .calorie(nutrientWeight.getWeight() * nutrientCodeCalorieMap.get(nutrientEnum.getCode()))
                            .build();
                }).collect(Collectors.toList());
        double calorieSum = nutrientWeightList.stream().mapToDouble(NutrientIntakesWeightAo::getCalorie).sum();
        if (targetCalorie != null) {
            // 热量计算误差处理
            nutrientWeightList.forEach(nutrientWeight -> {
                nutrientWeight.setPercent(nutrientWeight.getCalorie() / calorieSum);
                nutrientWeight.setCalorie(nutrientWeight.getPercent() * targetCalorie);
            });
        } else {
            nutrientWeightList
                    .forEach(nutrientWeight -> nutrientWeight.setPercent(nutrientWeight.getCalorie() / calorieSum));
        }
        return nutrientWeightList;

    }

}
