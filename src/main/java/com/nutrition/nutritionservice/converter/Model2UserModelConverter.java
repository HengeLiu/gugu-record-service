package com.nutrition.nutritionservice.converter;

import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientCategoryModelVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientCategoryModelVo;

/**
 * @author heng.liu
 * @since 2020/12/22
 */
public class Model2UserModelConverter {

    public static UserIngredientCategoryModelVo convert(ModelIngredientCategoryModelVo modelIngredientCategoryModelVo) {
        if (modelIngredientCategoryModelVo == null) {
            return null;
        }
        UserIngredientCategoryModelVo userIngredientCategoryModelVo = new UserIngredientCategoryModelVo();
        userIngredientCategoryModelVo.setCalorie(modelIngredientCategoryModelVo.getTargetCalorie());
        userIngredientCategoryModelVo.setProcessedGrains(modelIngredientCategoryModelVo.getProcessedGrains());
        userIngredientCategoryModelVo.setUnprocessedGrains(modelIngredientCategoryModelVo.getUnprocessedGrains());
        userIngredientCategoryModelVo.setMixedBeans(modelIngredientCategoryModelVo.getMixedBeans());
        userIngredientCategoryModelVo.setTuber(modelIngredientCategoryModelVo.getTuber());
        userIngredientCategoryModelVo.setGeneralVegetables(modelIngredientCategoryModelVo.getGeneralVegetables());
        userIngredientCategoryModelVo.setDarkVegetables(modelIngredientCategoryModelVo.getDarkVegetables());
        userIngredientCategoryModelVo.setFruit(modelIngredientCategoryModelVo.getFruit());
        userIngredientCategoryModelVo.setMeat(modelIngredientCategoryModelVo.getMeat());
        userIngredientCategoryModelVo.setPoultry(modelIngredientCategoryModelVo.getPoultry());
        userIngredientCategoryModelVo.setAquatic(modelIngredientCategoryModelVo.getAquatic());
        userIngredientCategoryModelVo.setEgg(modelIngredientCategoryModelVo.getEgg());
        userIngredientCategoryModelVo.setDairy(modelIngredientCategoryModelVo.getDairy());
        userIngredientCategoryModelVo.setSoybean(modelIngredientCategoryModelVo.getSoybean());
        userIngredientCategoryModelVo.setNut(modelIngredientCategoryModelVo.getNut());
        userIngredientCategoryModelVo.setOil(modelIngredientCategoryModelVo.getOil());
        userIngredientCategoryModelVo.setSalt(modelIngredientCategoryModelVo.getSalt());
        userIngredientCategoryModelVo.setGoal(modelIngredientCategoryModelVo.getGoal());

        return userIngredientCategoryModelVo;
    }

    public static ModelIngredientCategoryModelVo revert(UserIngredientCategoryModelVo userIngredientCategoryModelVo) {
        if (userIngredientCategoryModelVo == null) {
            return null;
        }
        ModelIngredientCategoryModelVo modelIngredientCategoryModelVo = new ModelIngredientCategoryModelVo();
        modelIngredientCategoryModelVo.setTargetCalorie(userIngredientCategoryModelVo.getCalorie());
        modelIngredientCategoryModelVo.setProcessedGrains(userIngredientCategoryModelVo.getProcessedGrains());
        modelIngredientCategoryModelVo.setUnprocessedGrains(userIngredientCategoryModelVo.getUnprocessedGrains());
        modelIngredientCategoryModelVo.setMixedBeans(userIngredientCategoryModelVo.getMixedBeans());
        modelIngredientCategoryModelVo.setTuber(userIngredientCategoryModelVo.getTuber());
        modelIngredientCategoryModelVo.setGeneralVegetables(userIngredientCategoryModelVo.getGeneralVegetables());
        modelIngredientCategoryModelVo.setDarkVegetables(userIngredientCategoryModelVo.getDarkVegetables());
        modelIngredientCategoryModelVo.setFruit(userIngredientCategoryModelVo.getFruit());
        modelIngredientCategoryModelVo.setMeat(userIngredientCategoryModelVo.getMeat());
        modelIngredientCategoryModelVo.setPoultry(userIngredientCategoryModelVo.getPoultry());
        modelIngredientCategoryModelVo.setAquatic(userIngredientCategoryModelVo.getAquatic());
        modelIngredientCategoryModelVo.setEgg(userIngredientCategoryModelVo.getEgg());
        modelIngredientCategoryModelVo.setDairy(userIngredientCategoryModelVo.getDairy());
        modelIngredientCategoryModelVo.setSoybean(userIngredientCategoryModelVo.getSoybean());
        modelIngredientCategoryModelVo.setNut(userIngredientCategoryModelVo.getNut());
        modelIngredientCategoryModelVo.setOil(userIngredientCategoryModelVo.getOil());
        modelIngredientCategoryModelVo.setSalt(userIngredientCategoryModelVo.getSalt());
        modelIngredientCategoryModelVo.setGoal(userIngredientCategoryModelVo.getGoal());
        return modelIngredientCategoryModelVo;
    }
}
