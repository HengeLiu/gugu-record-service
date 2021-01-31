package com.nutrition.nutritionservice.converter;

import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.controller.ao.NutrientWeightAo;
import com.nutrition.nutritionservice.enums.database.NutrientEnum;
import com.nutrition.nutritionservice.vo.NutrientWeightVo;

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
        if (nutrientWeightList == null) {
            nutrientWeightList = Collections.emptyList();
        }
        // 需要展示或计算热量的营养素，key: NutrientEnum，value: 营养素的热量。
        Map<NutrientEnum, Double> nutrientEnumCalorieMap = Maps.newTreeMap();
        nutrientEnumCalorieMap.put(NutrientEnum.Protein, 4.0);
        nutrientEnumCalorieMap.put(NutrientEnum.Fat, 9.0);
        nutrientEnumCalorieMap.put(NutrientEnum.CHO, 4.0);

        Map<Integer, Double> nutrientWeightMap = nutrientWeightList.stream()
                .collect(Collectors.toMap(NutrientWeightVo::getNutrientCode, NutrientWeightVo::getWeight));
        List<NutrientWeightAo> nutrientWeightAoList = nutrientEnumCalorieMap.entrySet().stream()
                .map(nutrientCodeCalorieEntry -> {
                    NutrientEnum nutrientEnum = nutrientCodeCalorieEntry.getKey();
                    Double nutrientCalorie = nutrientCodeCalorieEntry.getValue();
                    Double nutrientWeight = nutrientWeightMap.getOrDefault(nutrientEnum.getCode(), 0.0);
                    return NutrientWeightAo.builder().nutrientCode(nutrientEnum.getCode())
                            .nutrientName(nutrientEnum.getNameZh()).weight(nutrientWeight)
                            .calorie(nutrientWeight * nutrientCalorie).build();
                }).collect(Collectors.toList());
        double calorieSum = nutrientWeightAoList.stream().mapToDouble(NutrientWeightAo::getCalorie).sum();
        if (calorieSum > 5) {
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
        } else {
            nutrientWeightAoList
                    .forEach(nutrientWeight -> nutrientWeight.setPercent(0.0));
        }
        return nutrientWeightAoList;

    }

}
