package com.nutrition.nutritionservice.converter;

import com.nutrition.nutritionservice.vo.modeldata.IntakesModelVo;
import com.nutrition.nutritionservice.vo.user.UserCategoryIntakesModelVo;

/**
 * @author heng.liu
 * @since 2020/12/22
 */
public class IntakesModel2UserModelConverter
        extends BaseConverter<IntakesModelVo, UserCategoryIntakesModelVo> {

    public static final IntakesModel2UserModelConverter INSTANCE = new IntakesModel2UserModelConverter();

    private IntakesModel2UserModelConverter() {
    }

    @Override
    protected UserCategoryIntakesModelVo doForward(IntakesModelVo intakesModelVo) {
        UserCategoryIntakesModelVo userCategoryIntakesModelVo = new UserCategoryIntakesModelVo();
        userCategoryIntakesModelVo.setId(intakesModelVo.getId());
        userCategoryIntakesModelVo.setCalorie(intakesModelVo.getCalorie());
        userCategoryIntakesModelVo.setProcessedGrains(intakesModelVo.getProcessedGrains());
        userCategoryIntakesModelVo.setUnprocessedGrains(intakesModelVo.getUnprocessedGrains());
        userCategoryIntakesModelVo.setMixedBeans(intakesModelVo.getMixedBeans());
        userCategoryIntakesModelVo.setTuber(intakesModelVo.getTuber());
        userCategoryIntakesModelVo.setGeneralVegetables(intakesModelVo.getGeneralVegetables());
        userCategoryIntakesModelVo.setDarkVegetables(intakesModelVo.getDarkVegetables());
        userCategoryIntakesModelVo.setFruit(intakesModelVo.getFruit());
        userCategoryIntakesModelVo.setMeat(intakesModelVo.getMeat());
        userCategoryIntakesModelVo.setPoultry(intakesModelVo.getPoultry());
        userCategoryIntakesModelVo.setAquatic(intakesModelVo.getAquatic());
        userCategoryIntakesModelVo.setEgg(intakesModelVo.getEgg());
        userCategoryIntakesModelVo.setDairy(intakesModelVo.getDairy());
        userCategoryIntakesModelVo.setSoybean(intakesModelVo.getSoybean());
        userCategoryIntakesModelVo.setNut(intakesModelVo.getNut());
        userCategoryIntakesModelVo.setOil(intakesModelVo.getOil());
        userCategoryIntakesModelVo.setSalt(intakesModelVo.getSalt());
        userCategoryIntakesModelVo.setGoal(intakesModelVo.getGoal());
        return userCategoryIntakesModelVo;
    }

    @Override
    protected IntakesModelVo doBackward(UserCategoryIntakesModelVo userCategoryIntakesModelVo) {
        IntakesModelVo intakesModelVo = new IntakesModelVo();
        intakesModelVo.setId(userCategoryIntakesModelVo.getId());
        intakesModelVo.setCalorie(userCategoryIntakesModelVo.getCalorie());
        intakesModelVo.setProcessedGrains(userCategoryIntakesModelVo.getProcessedGrains());
        intakesModelVo.setUnprocessedGrains(userCategoryIntakesModelVo.getUnprocessedGrains());
        intakesModelVo.setMixedBeans(userCategoryIntakesModelVo.getMixedBeans());
        intakesModelVo.setTuber(userCategoryIntakesModelVo.getTuber());
        intakesModelVo.setGeneralVegetables(userCategoryIntakesModelVo.getGeneralVegetables());
        intakesModelVo.setDarkVegetables(userCategoryIntakesModelVo.getDarkVegetables());
        intakesModelVo.setFruit(userCategoryIntakesModelVo.getFruit());
        intakesModelVo.setMeat(userCategoryIntakesModelVo.getMeat());
        intakesModelVo.setPoultry(userCategoryIntakesModelVo.getPoultry());
        intakesModelVo.setAquatic(userCategoryIntakesModelVo.getAquatic());
        intakesModelVo.setEgg(userCategoryIntakesModelVo.getEgg());
        intakesModelVo.setDairy(userCategoryIntakesModelVo.getDairy());
        intakesModelVo.setSoybean(userCategoryIntakesModelVo.getSoybean());
        intakesModelVo.setNut(userCategoryIntakesModelVo.getNut());
        intakesModelVo.setOil(userCategoryIntakesModelVo.getOil());
        intakesModelVo.setSalt(userCategoryIntakesModelVo.getSalt());
        intakesModelVo.setGoal(userCategoryIntakesModelVo.getGoal());
        return intakesModelVo;
    }
}
