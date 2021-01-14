package com.nutrition.nutritionservice.converter;

import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.controller.health.ao.NutrientWeightAo;
import com.nutrition.nutritionservice.enums.CodeEnums;
import com.nutrition.nutritionservice.enums.database.NutrientEnum;
import com.nutrition.nutritionservice.vo.NutrientWeightVo;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author heng.liu
 * @since 2021/1/12
 */
public class NutrientWeightVo2AoConverter {

    /**
     * 计算用户营养素每日摄入历史重量。
     *
     * @param targetCalorie 所有营养素素总热量，将根据该热量处理计算误差。为 null 时不进行热量误差修正。
     * @param nutrientWeightList 营养素重量列表。
     * @return 营养素摄入历史列表
     */
    public static List<NutrientWeightAo> convert(Double targetCalorie,
            List<? extends NutrientWeightVo> nutrientWeightList) {
        // 需要展示或计算热量的营养素，key: 营养素编码，value: 营养素的热量。
        Map<Integer, Double> nutrientCodeCalorieMap = Maps.newHashMap();
        nutrientCodeCalorieMap.put(NutrientEnum.CHO.getCode(), 4.0);
        nutrientCodeCalorieMap.put(NutrientEnum.Fat.getCode(), 9.0);
        nutrientCodeCalorieMap.put(NutrientEnum.Protein.getCode(), 4.0);
        if (CollectionUtils.isEmpty(nutrientWeightList)) {
            return Collections.emptyList();
        }
        List<NutrientWeightAo> nutrientWeightAoList = nutrientWeightList.stream()
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
        double calorieSum = nutrientWeightAoList.stream().mapToDouble(NutrientWeightAo::getCalorie).sum();
        if (targetCalorie != null) {
            // 热量计算误差处理
            nutrientWeightAoList.forEach(nutrientWeight -> {
                nutrientWeight.setPercent(nutrientWeight.getCalorie() / calorieSum);
                nutrientWeight.setCalorie(nutrientWeight.getPercent() * targetCalorie);
            });
        } else {
            nutrientWeightAoList
                    .forEach(nutrientWeight -> nutrientWeight.setPercent(nutrientWeight.getCalorie() / calorieSum));
        }
        return nutrientWeightAoList;

    }

}
