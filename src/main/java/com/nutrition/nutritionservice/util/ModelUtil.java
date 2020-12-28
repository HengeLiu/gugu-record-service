package com.nutrition.nutritionservice.util;

import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.enums.database.IngredientCategoryEnum;
import com.nutrition.nutritionservice.vo.modeldata.CategoryModel;
import com.nutrition.nutritionservice.vo.modeldata.IntakesModelVo;
import org.springframework.util.NumberUtils;

import java.util.Map;
import java.util.Vector;

/**
 * 模型工具
 *
 * @author heng.liu
 * @since 2020/12/28
 */
public class ModelUtil {

    public static <T> Vector<T> modelToVector(CategoryModel<T> model) {
        Vector<T> v = new Vector<>();
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

    public static double calculateCosineSimilarity(CategoryModel<? extends Number> model1,
            CategoryModel<? extends Number> model2) {
        Vector<? extends Number> v1 = ModelUtil.modelToVector(model1);
        Vector<? extends Number> v2 = ModelUtil.modelToVector(model2);
        return VectorUtil.cosineSimilarity(v1, v2);
    }

    public static <T extends Number> double calculateEuclidDistance(CategoryModel<T> model1, CategoryModel<T> model2) {
        Vector<T> v1 = ModelUtil.modelToVector(model1);
        Vector<T> v2 = ModelUtil.modelToVector(model2);
        return VectorUtil.euclidDistance(v1, v2);
    }

    public static <T> void categoryEnumMapToModel(Map<IngredientCategoryEnum, T> categoryEnumMap,
            CategoryModel<T> model) {
        model.setProcessedGrains(categoryEnumMap.get(IngredientCategoryEnum.PROCESSED_GRAINS));
        model.setUnprocessedGrains(categoryEnumMap.get(IngredientCategoryEnum.UNPROCESSED_GRAINS));
        model.setMixedBeans(categoryEnumMap.get(IngredientCategoryEnum.MIXED_BEANS));
        model.setTuber(categoryEnumMap.get(IngredientCategoryEnum.TUBER));
        model.setGeneralVegetables(categoryEnumMap.get(IngredientCategoryEnum.GENERAL_VEGETABLES));
        model.setDarkVegetables(categoryEnumMap.get(IngredientCategoryEnum.DARK_VEGETABLES));
        model.setFruit(categoryEnumMap.get(IngredientCategoryEnum.FRUITS));
        model.setMeat(categoryEnumMap.get(IngredientCategoryEnum.MEAT));
        model.setPoultry(categoryEnumMap.get(IngredientCategoryEnum.POULTRY));
        model.setAquatic(categoryEnumMap.get(IngredientCategoryEnum.AQUATIC));
        model.setEgg(categoryEnumMap.get(IngredientCategoryEnum.EGGS));
        model.setDairy(categoryEnumMap.get(IngredientCategoryEnum.DAIRY));
        model.setSoybean(categoryEnumMap.get(IngredientCategoryEnum.SOYBEAN));
        model.setNut(categoryEnumMap.get(IngredientCategoryEnum.NUT));
        model.setOil(categoryEnumMap.get(IngredientCategoryEnum.OIL));
        model.setSalt(categoryEnumMap.get(IngredientCategoryEnum.SALT));
    }

    public static <T> Map<IngredientCategoryEnum, T> modelToCategoryEnumMap(CategoryModel<T> intakesModelVo) {
        Map<IngredientCategoryEnum, T> modelCategoryMap = Maps.newHashMap();
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

}
