package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.controller.ao.NutrientWeightAo;
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
     * @param targetCalorie 所有营养素素总热量，将根据该热量处理计算误差。
     * @return 营养素摄入历史列表
     */
    public List<NutrientWeightAo> calculateToAo(Double targetCalorie,
                                                List<UserNutrientWeightSumDailyVo> userNutrientWeightSumDailyVoList) {
        // 需要展示或计算热量的营养素，key: 营养素编码，value: 营养素的热量。
        Map<Integer, Double> nutrientCodeCalorieMap = Maps.newHashMap();
        nutrientCodeCalorieMap.put(NutrientEnum.CHO.getCode(), 4.0);
        nutrientCodeCalorieMap.put(NutrientEnum.Fat.getCode(), 9.0);
        nutrientCodeCalorieMap.put(NutrientEnum.Protein.getCode(), 4.0);
        if (CollectionUtils.isEmpty(userNutrientWeightSumDailyVoList)) {
            return Collections.emptyList();
        }
        List<NutrientWeightAo> nutrientWeightList = userNutrientWeightSumDailyVoList.stream()
                .filter(nutrientWeight -> nutrientCodeCalorieMap.containsKey(nutrientWeight.getNutrientCode()))
                .map(nutrientWeight -> {
                    NutrientEnum nutrientEnum = CodeEnums.valueOf(NutrientEnum.class, nutrientWeight.getNutrientCode());
                    if (nutrientEnum == null) {
                        throw new IllegalArgumentException(
                                "Nutrient not exist, code " + nutrientWeight.getNutrientCode());
                    }
                    return NutrientWeightAo.builder().nutrientCode(nutrientEnum.getCode())
                            .nutrientName(nutrientEnum.getNameZh()).weight(nutrientWeight.getWeight())
                            .calorie(nutrientWeight.getWeight() * nutrientCodeCalorieMap.get(nutrientEnum.getCode()))
                            .build();
                }).collect(Collectors.toList());
        double calorieSum = nutrientWeightList.stream().mapToDouble(NutrientWeightAo::getCalorie).sum();
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

    public List<NutrientWeightAo> queryUserNutrientWeightSumDaily(String uuid, Double targetCalorie,
                                                                  LocalDate date) {
        List<UserNutrientWeightSumDailyVo> userNutrientWeightSumDailyVoList = userNutrientWeightSumDailyService
                .queryByUuidAndDate(uuid, date);
        return this.calculateToAo(targetCalorie, userNutrientWeightSumDailyVoList);
    }
}
