package com.nutrition.nutritionservice.converter;

import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientIntakesVo;
import com.nutrition.nutritionservice.vo.user.UserCategoryIntakesModelVo;

/**
 * @author heng.liu
 * @since 2020/12/22
 */
public class IntakesModel2UserModelConverter
        extends BaseConverter<ModelIngredientIntakesVo, UserCategoryIntakesModelVo> {

    public static final IntakesModel2UserModelConverter INSTANCE = new IntakesModel2UserModelConverter();

    private IntakesModel2UserModelConverter() {
    }

    @Override
    protected UserCategoryIntakesModelVo doForward(ModelIngredientIntakesVo modelIngredientIntakesVo) {
        UserCategoryIntakesModelVo userCategoryIntakesModelVo = new UserCategoryIntakesModelVo();
        userCategoryIntakesModelVo.setId(modelIngredientIntakesVo.getId());
        userCategoryIntakesModelVo.setCalorie(modelIngredientIntakesVo.getCalorie());
        userCategoryIntakesModelVo.setProcessedGrains(modelIngredientIntakesVo.getProcessedGrains());
        userCategoryIntakesModelVo.setUnprocessedGrains(modelIngredientIntakesVo.getUnprocessedGrains());
        userCategoryIntakesModelVo.setMixedBeans(modelIngredientIntakesVo.getMixedBeans());
        userCategoryIntakesModelVo.setTuber(modelIngredientIntakesVo.getTuber());
        userCategoryIntakesModelVo.setGeneralVegetables(modelIngredientIntakesVo.getGeneralVegetables());
        userCategoryIntakesModelVo.setDarkVegetables(modelIngredientIntakesVo.getDarkVegetables());
        userCategoryIntakesModelVo.setFruit(modelIngredientIntakesVo.getFruit());
        userCategoryIntakesModelVo.setMeat(modelIngredientIntakesVo.getMeat());
        userCategoryIntakesModelVo.setPoultry(modelIngredientIntakesVo.getPoultry());
        userCategoryIntakesModelVo.setAquatic(modelIngredientIntakesVo.getAquatic());
        userCategoryIntakesModelVo.setEgg(modelIngredientIntakesVo.getEgg());
        userCategoryIntakesModelVo.setDairy(modelIngredientIntakesVo.getDairy());
        userCategoryIntakesModelVo.setSoybean(modelIngredientIntakesVo.getSoybean());
        userCategoryIntakesModelVo.setNut(modelIngredientIntakesVo.getNut());
        userCategoryIntakesModelVo.setOil(modelIngredientIntakesVo.getOil());
        userCategoryIntakesModelVo.setSalt(modelIngredientIntakesVo.getSalt());
        userCategoryIntakesModelVo.setGoal(modelIngredientIntakesVo.getGoal());
        return userCategoryIntakesModelVo;
    }

    @Override
    protected ModelIngredientIntakesVo doBackward(UserCategoryIntakesModelVo userCategoryIntakesModelVo) {
        ModelIngredientIntakesVo modelIngredientIntakesVo = new ModelIngredientIntakesVo();
        modelIngredientIntakesVo.setId(userCategoryIntakesModelVo.getId());
        modelIngredientIntakesVo.setCalorie(userCategoryIntakesModelVo.getCalorie());
        modelIngredientIntakesVo.setProcessedGrains(userCategoryIntakesModelVo.getProcessedGrains());
        modelIngredientIntakesVo.setUnprocessedGrains(userCategoryIntakesModelVo.getUnprocessedGrains());
        modelIngredientIntakesVo.setMixedBeans(userCategoryIntakesModelVo.getMixedBeans());
        modelIngredientIntakesVo.setTuber(userCategoryIntakesModelVo.getTuber());
        modelIngredientIntakesVo.setGeneralVegetables(userCategoryIntakesModelVo.getGeneralVegetables());
        modelIngredientIntakesVo.setDarkVegetables(userCategoryIntakesModelVo.getDarkVegetables());
        modelIngredientIntakesVo.setFruit(userCategoryIntakesModelVo.getFruit());
        modelIngredientIntakesVo.setMeat(userCategoryIntakesModelVo.getMeat());
        modelIngredientIntakesVo.setPoultry(userCategoryIntakesModelVo.getPoultry());
        modelIngredientIntakesVo.setAquatic(userCategoryIntakesModelVo.getAquatic());
        modelIngredientIntakesVo.setEgg(userCategoryIntakesModelVo.getEgg());
        modelIngredientIntakesVo.setDairy(userCategoryIntakesModelVo.getDairy());
        modelIngredientIntakesVo.setSoybean(userCategoryIntakesModelVo.getSoybean());
        modelIngredientIntakesVo.setNut(userCategoryIntakesModelVo.getNut());
        modelIngredientIntakesVo.setOil(userCategoryIntakesModelVo.getOil());
        modelIngredientIntakesVo.setSalt(userCategoryIntakesModelVo.getSalt());
        modelIngredientIntakesVo.setGoal(userCategoryIntakesModelVo.getGoal());
        return modelIngredientIntakesVo;
    }
}
