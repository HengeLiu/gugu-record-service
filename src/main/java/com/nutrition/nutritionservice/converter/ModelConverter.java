package com.nutrition.nutritionservice.converter;

import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.enums.database.IngredientCategoryEnum;
import com.nutrition.nutritionservice.vo.modeldata.IntakesModelVo;

import java.util.Map;

/**
 * @author heng.liu
 * @since 2020/12/24
 */
public class ModelConverter {

    public static final ModelConverter INSTANCE = new ModelConverter();

    private ModelConverter() {
    }

    public Map<IngredientCategoryEnum, Integer> modelToCategoryMap(IntakesModelVo intakesModelVo){
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
        modelCategoryMap.put(IngredientCategoryEnum.DAIRY  , intakesModelVo.getDairy());
        modelCategoryMap.put(IngredientCategoryEnum.SOYBEAN, intakesModelVo.getSoybean());
        modelCategoryMap.put(IngredientCategoryEnum.NUT, intakesModelVo.getNut());
        modelCategoryMap.put(IngredientCategoryEnum.OIL, intakesModelVo.getOil());
        modelCategoryMap.put(IngredientCategoryEnum.SALT, intakesModelVo.getSalt());
        return modelCategoryMap;
    }

}
