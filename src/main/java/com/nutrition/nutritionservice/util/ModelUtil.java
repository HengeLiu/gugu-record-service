package com.nutrition.nutritionservice.util;

import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.controller.ao.IngredientCategoryWeightAo;
import com.nutrition.nutritionservice.controller.ao.SupperIngredientCategoryWeightAo;
import com.nutrition.nutritionservice.enums.database.IngredientCategoryEnum;
import com.nutrition.nutritionservice.enums.database.IngredientSuperCategoryEnum;
import com.nutrition.nutritionservice.vo.CuisineIngredientCategoryWeightVo;
import com.nutrition.nutritionservice.vo.modeldata.CategoryModel;
import com.nutrition.nutritionservice.vo.user.UserIngredientCategoryModelVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientWeightSumDailyVo;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * 模型工具
 *
 * @author heng.liu
 * @since 2020/12/28
 */
public class ModelUtil {

    public static Vector<Integer> modelToVector(CategoryModel model) {
        if (model == null) {
            return new Vector<>();
        }
        Vector<Integer> v = new Vector<>();
        v.add(0, model.getProcessedGrains());
        v.add(1, model.getUnprocessedGrains());
        v.add(2, model.getMixedBeans());
        v.add(3, model.getTuber());
        v.add(4, model.getGeneralVegetables());
        v.add(5, model.getDarkVegetables());
        v.add(6, model.getFruit());
        v.add(7, model.getMeat());
        v.add(8, model.getPoultry());
        v.add(9, model.getAquatic());
        v.add(10, model.getEgg());
        v.add(11, model.getDairy());
        v.add(12, model.getSoybean());
        v.add(13, model.getNut());
        v.add(14, model.getOil());
        v.add(15, model.getSalt());
        return v;
    }

    public static void vectorToModel(Vector<Integer> vector, CategoryModel targetModel) {
        if (vector.size() != 16) {
            throw new UnsupportedOperationException(
                    "Model vector length error, length " + vector.size() + ", target length 16");
        }
        targetModel.setProcessedGrains(vector.get(0));
        targetModel.setUnprocessedGrains(vector.get(1));
        targetModel.setMixedBeans(vector.get(2));
        targetModel.setTuber(vector.get(3));
        targetModel.setGeneralVegetables(vector.get(4));
        targetModel.setDarkVegetables(vector.get(5));
        targetModel.setFruit(vector.get(6));
        targetModel.setMeat(vector.get(7));
        targetModel.setPoultry(vector.get(8));
        targetModel.setAquatic(vector.get(9));
        targetModel.setEgg(vector.get(10));
        targetModel.setDairy(vector.get(11));
        targetModel.setSoybean(vector.get(12));
        targetModel.setNut(vector.get(13));
        targetModel.setOil(vector.get(14));
        targetModel.setSalt(vector.get(15));
    }

    public static double calculateCosineSimilarity(CategoryModel model1, CategoryModel model2) {
        Vector<? extends Number> v1 = ModelUtil.modelToVector(model1);
        Vector<? extends Number> v2 = ModelUtil.modelToVector(model2);
        return VectorUtil.cosineSimilarity(v1, v2);
    }

    public static <T extends Number> double calculateEuclidDistance(CategoryModel model1, CategoryModel model2) {
        Vector<Integer> v1 = ModelUtil.modelToVector(model1);
        Vector<Integer> v2 = ModelUtil.modelToVector(model2);
        return VectorUtil.euclidDistance(v1, v2);
    }

    public static CategoryModel categoryEnumMapToModel(Map<IngredientCategoryEnum, Integer> categoryEnumMap,
            CategoryModel model) {
        model.setProcessedGrains(categoryEnumMap.getOrDefault(IngredientCategoryEnum.PROCESSED_GRAINS, 0));
        model.setUnprocessedGrains(categoryEnumMap.getOrDefault(IngredientCategoryEnum.UNPROCESSED_GRAINS, 0));
        model.setMixedBeans(categoryEnumMap.getOrDefault(IngredientCategoryEnum.MIXED_BEANS, 0));
        model.setTuber(categoryEnumMap.getOrDefault(IngredientCategoryEnum.TUBER, 0));
        model.setGeneralVegetables(categoryEnumMap.getOrDefault(IngredientCategoryEnum.GENERAL_VEGETABLES, 0));
        model.setDarkVegetables(categoryEnumMap.getOrDefault(IngredientCategoryEnum.DARK_VEGETABLES, 0));
        model.setFruit(categoryEnumMap.getOrDefault(IngredientCategoryEnum.FRUITS, 0));
        model.setMeat(categoryEnumMap.getOrDefault(IngredientCategoryEnum.MEAT, 0));
        model.setPoultry(categoryEnumMap.getOrDefault(IngredientCategoryEnum.POULTRY, 0));
        model.setAquatic(categoryEnumMap.getOrDefault(IngredientCategoryEnum.AQUATIC, 0));
        model.setEgg(categoryEnumMap.getOrDefault(IngredientCategoryEnum.EGGS, 0));
        model.setDairy(categoryEnumMap.getOrDefault(IngredientCategoryEnum.DAIRY, 0));
        model.setSoybean(categoryEnumMap.getOrDefault(IngredientCategoryEnum.SOYBEAN, 0));
        model.setNut(categoryEnumMap.getOrDefault(IngredientCategoryEnum.NUT, 0));
        model.setOil(categoryEnumMap.getOrDefault(IngredientCategoryEnum.OIL, 0));
        model.setSalt(categoryEnumMap.getOrDefault(IngredientCategoryEnum.SALT, 0));
        return model;
    }

    public static void fillValue(CategoryModel model, Integer value) {
        if (model.getProcessedGrains() == null) {
            model.setProcessedGrains(value);
        }
        if (model.getUnprocessedGrains() == null) {
            model.setUnprocessedGrains(value);
        }
        if (model.getMixedBeans() == null) {
            model.setMixedBeans(value);
        }
        if (model.getTuber() == null) {
            model.setTuber(value);
        }
        if (model.getGeneralVegetables() == null) {
            model.setGeneralVegetables(value);
        }
        if (model.getDarkVegetables() == null) {
            model.setDarkVegetables(value);
        }
        if (model.getFruit() == null) {
            model.setFruit(value);
        }
        if (model.getMeat() == null) {
            model.setMeat(value);
        }
        if (model.getPoultry() == null) {
            model.setPoultry(value);
        }
        if (model.getAquatic() == null) {
            model.setAquatic(value);
        }
        if (model.getEgg() == null) {
            model.setEgg(value);
        }
        if (model.getDairy() == null) {
            model.setDairy(value);
        }
        if (model.getSoybean() == null) {
            model.setSoybean(value);
        }
        if (model.getNut() == null) {
            model.setNut(value);
        }
        if (model.getOil() == null) {
            model.setOil(value);
        }
        if (model.getSalt() == null) {
            model.setSalt(value);
        }
    }

    public static Map<IngredientCategoryEnum, Integer> modelToCategoryEnumMap(CategoryModel intakesModelVo) {
        Map<IngredientCategoryEnum, Integer> modelCategoryMap = Maps.newHashMap();
        modelCategoryMap.put(IngredientCategoryEnum.PROCESSED_GRAINS, intakesModelVo.getProcessedGrains());
        modelCategoryMap.put(IngredientCategoryEnum.UNPROCESSED_GRAINS, intakesModelVo.getUnprocessedGrains());
        modelCategoryMap.put(IngredientCategoryEnum.MIXED_BEANS, intakesModelVo.getMixedBeans());
        modelCategoryMap.put(IngredientCategoryEnum.TUBER, intakesModelVo.getTuber());
        modelCategoryMap.put(IngredientCategoryEnum.GENERAL_VEGETABLES, intakesModelVo.getGeneralVegetables());
        modelCategoryMap.put(IngredientCategoryEnum.DARK_VEGETABLES, intakesModelVo.getDarkVegetables());
        modelCategoryMap.put(IngredientCategoryEnum.FRUITS, intakesModelVo.getFruit());
        modelCategoryMap.put(IngredientCategoryEnum.MEAT, intakesModelVo.getMeat());
        modelCategoryMap.put(IngredientCategoryEnum.POULTRY, intakesModelVo.getPoultry());
        modelCategoryMap.put(IngredientCategoryEnum.AQUATIC, intakesModelVo.getAquatic());
        modelCategoryMap.put(IngredientCategoryEnum.EGGS, intakesModelVo.getEgg());
        modelCategoryMap.put(IngredientCategoryEnum.DAIRY, intakesModelVo.getDairy());
        modelCategoryMap.put(IngredientCategoryEnum.SOYBEAN, intakesModelVo.getSoybean());
        modelCategoryMap.put(IngredientCategoryEnum.NUT, intakesModelVo.getNut());
        modelCategoryMap.put(IngredientCategoryEnum.OIL, intakesModelVo.getOil());
        modelCategoryMap.put(IngredientCategoryEnum.SALT, intakesModelVo.getSalt());
        return modelCategoryMap;
    }

    public static List<SupperIngredientCategoryWeightAo> modelHistoryToWeightAo(UserIngredientCategoryModelVo targetModel,
                                                                                UserIngredientWeightSumDailyVo historicalModel) {
        Map<IngredientCategoryEnum, Integer> targetMap = modelToCategoryEnumMap(targetModel);
        Map<IngredientCategoryEnum, Integer> historicalMap = modelToCategoryEnumMap(historicalModel);
        return targetMap.entrySet().stream()
                // 先按照食材大类分组
                .collect(Collectors.groupingBy(entry -> entry.getKey().getParentCategory()))
                // 遍历每一个食材大类
                .entrySet().stream().map(superCategoryEntry -> {
                    List<IngredientCategoryWeightAo> ingredientCategoryWeightAoList = superCategoryEntry.getValue()
                            // 遍历每一个食材分类
                            .stream().map(categoryEntry -> {
                                IngredientCategoryEnum categoryEnum = categoryEntry.getKey();
                                return IngredientCategoryWeightAo.builder().categoryName(categoryEnum.getNameZh())
                                        .categoryCode(categoryEnum.getCode())
                                        // 摄入目标值
                                        .targetWeight(categoryEntry.getValue())
                                        // 摄入历史值
                                        .historicalWeight(historicalMap.get(categoryEnum).intValue())
                                        .commonIngredientNameListStr(categoryEnum.getCommonIngredientNameListStr())
                                        .build();
                            }).collect(Collectors.toList());
                    // 计算大类下分类总目标重量
                    int targetWeight = ingredientCategoryWeightAoList.stream()
                            .mapToInt(IngredientCategoryWeightAo::getTargetWeight).sum();
                    // 计算大类下分类总历史重量
                    int historicalWeight = ingredientCategoryWeightAoList.stream()
                            .mapToInt(IngredientCategoryWeightAo::getHistoricalWeight).sum();
                    IngredientSuperCategoryEnum superCategoryEnum = superCategoryEntry.getKey();
                    return SupperIngredientCategoryWeightAo.builder()
                            .supperCategoryName(superCategoryEnum.getNameZh())
                            .supperCategoryCode(superCategoryEnum.getCode())
                            .supperCategoryTargetWeight(targetWeight)
                            .supperCategoryHistoricalWeight(historicalWeight)
                            .categoryWeightList(ingredientCategoryWeightAoList).build();
                }).collect(Collectors.toList());
    }

    public static List<SupperIngredientCategoryWeightAo> cuisineIngredientCategoryWeightVoToAo(
            CuisineIngredientCategoryWeightVo cuisineIngredientCategoryWeightVo) {
        Map<IngredientCategoryEnum, Integer> targetMap = modelToCategoryEnumMap(cuisineIngredientCategoryWeightVo);
        return targetMap.entrySet().stream()
                // 先按照食材大类分组
                .collect(Collectors.groupingBy(entry -> entry.getKey().getParentCategory()))
                // 遍历每一个食材大类
                .entrySet().stream().map(superCategoryEntry -> {
                    List<IngredientCategoryWeightAo> ingredientCategoryWeightAoList = superCategoryEntry.getValue()
                            // 遍历每一个食材分类
                            .stream().map(categoryEntry -> {
                                IngredientCategoryEnum categoryEnum = categoryEntry.getKey();
                                return IngredientCategoryWeightAo.builder().categoryName(categoryEnum.getNameZh())
                                        .categoryCode(categoryEnum.getCode()).targetWeight(categoryEntry.getValue())
                                        .build();
                            }).collect(Collectors.toList());
                    IngredientSuperCategoryEnum superCategoryEnum = superCategoryEntry.getKey();
                    return SupperIngredientCategoryWeightAo.builder().supperCategoryName(superCategoryEnum.getNameZh())
                            .supperCategoryCode(superCategoryEnum.getCode()).supperCategoryTargetWeight(
                                    // 大类下分类总重量
                                    ingredientCategoryWeightAoList.stream()
                                            .mapToInt(IngredientCategoryWeightAo::getTargetWeight).sum())
                            .categoryWeightList(ingredientCategoryWeightAoList).build();
                }).collect(Collectors.toList());
    }
}
