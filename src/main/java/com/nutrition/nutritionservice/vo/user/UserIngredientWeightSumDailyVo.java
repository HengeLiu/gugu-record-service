package com.nutrition.nutritionservice.vo.user;

import com.nutrition.nutritionservice.vo.modeldata.CategoryModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * user_ingredient_weight_sum_daily
 * 
 * @author heng.liu
 * @since 2020/12/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserIngredientWeightSumDailyVo extends CategoryModel implements Serializable {
    private int id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 日期
     */
    private LocalDate date;

    /**
     * 当日累计摄入热量
     */
    private double calorie;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public static UserIngredientWeightSumDailyVo createEmpty(String uuid, LocalDate date) {
        UserIngredientWeightSumDailyVo emptyVo = new UserIngredientWeightSumDailyVo();
        emptyVo.setProcessedGrains(0);
        emptyVo.setUnprocessedGrains(0);
        emptyVo.setMixedBeans(0);
        emptyVo.setTuber(0);
        emptyVo.setGeneralVegetables(0);
        emptyVo.setDarkVegetables(0);
        emptyVo.setFruit(0);
        emptyVo.setMeat(0);
        emptyVo.setPoultry(0);
        emptyVo.setAquatic(0);
        emptyVo.setEgg(0);
        emptyVo.setDairy(0);
        emptyVo.setSoybean(0);
        emptyVo.setNut(0);
        emptyVo.setOil(0);
        emptyVo.setSalt(0);
        emptyVo.setUuid(uuid);
        emptyVo.setDate(date);
        emptyVo.setCalorie(0);
        return emptyVo;
    }

    public void addCuisineCategoryWeight(CategoryModel cuisineIngredientCategoryWeightVo, double calorie) {
        this.setProcessedGrains(this.getProcessedGrains() + cuisineIngredientCategoryWeightVo.getProcessedGrains());
        this.setUnprocessedGrains(this.getUnprocessedGrains() + cuisineIngredientCategoryWeightVo.getUnprocessedGrains());
        this.setMixedBeans(this.getMixedBeans() + cuisineIngredientCategoryWeightVo.getMixedBeans());
        this.setTuber(this.getTuber() + cuisineIngredientCategoryWeightVo.getTuber());
        this.setGeneralVegetables(this.getGeneralVegetables() + cuisineIngredientCategoryWeightVo.getGeneralVegetables());
        this.setDarkVegetables(this.getDarkVegetables() + cuisineIngredientCategoryWeightVo.getDarkVegetables());
        this.setFruit(this.getFruit() + cuisineIngredientCategoryWeightVo.getFruit());
        this.setMeat(this.getMeat() + cuisineIngredientCategoryWeightVo.getMeat());
        this.setPoultry(this.getPoultry() + cuisineIngredientCategoryWeightVo.getPoultry());
        this.setAquatic(this.getAquatic() + cuisineIngredientCategoryWeightVo.getAquatic());
        this.setEgg(this.getEgg() + cuisineIngredientCategoryWeightVo.getEgg());
        this.setDairy(this.getDairy() + cuisineIngredientCategoryWeightVo.getDairy());
        this.setSoybean(this.getSoybean() + cuisineIngredientCategoryWeightVo.getSoybean());
        this.setNut(this.getNut() + cuisineIngredientCategoryWeightVo.getNut());
        this.setOil(this.getOil() + cuisineIngredientCategoryWeightVo.getOil());
        this.setSalt(this.getSalt() + cuisineIngredientCategoryWeightVo.getSalt());
        this.setCalorie(this.getCalorie() + calorie);
    }

    public void minusCuisineCategoryWeight(CategoryModel cuisineIngredientCategoryWeightVo, double calorie) {
        this.setProcessedGrains(
                Math.max(this.getProcessedGrains() - cuisineIngredientCategoryWeightVo.getProcessedGrains(), 0));
        this.setUnprocessedGrains(
                Math.max(this.getUnprocessedGrains() - cuisineIngredientCategoryWeightVo.getUnprocessedGrains(), 0));
        this.setMixedBeans(Math.max(this.getMixedBeans() - cuisineIngredientCategoryWeightVo.getMixedBeans(), 0));
        this.setTuber(Math.max(this.getTuber() - cuisineIngredientCategoryWeightVo.getTuber(), 0));
        this.setGeneralVegetables(
                Math.max(this.getGeneralVegetables() - cuisineIngredientCategoryWeightVo.getGeneralVegetables(), 0));
        this.setDarkVegetables(
                Math.max(this.getDarkVegetables() - cuisineIngredientCategoryWeightVo.getDarkVegetables(), 0));
        this.setFruit(Math.max(this.getFruit() - cuisineIngredientCategoryWeightVo.getFruit(), 0));
        this.setMeat(Math.max(this.getMeat() - cuisineIngredientCategoryWeightVo.getMeat(), 0));
        this.setPoultry(Math.max(this.getPoultry() - cuisineIngredientCategoryWeightVo.getPoultry(), 0));
        this.setAquatic(Math.max(this.getAquatic() - cuisineIngredientCategoryWeightVo.getAquatic(), 0));
        this.setEgg(Math.max(this.getEgg() - cuisineIngredientCategoryWeightVo.getEgg(), 0));
        this.setDairy(Math.max(this.getDairy() - cuisineIngredientCategoryWeightVo.getDairy(), 0));
        this.setSoybean(Math.max(this.getSoybean() - cuisineIngredientCategoryWeightVo.getSoybean(), 0));
        this.setNut(Math.max(this.getNut() - cuisineIngredientCategoryWeightVo.getNut(), 0));
        this.setOil(Math.max(this.getOil() - cuisineIngredientCategoryWeightVo.getOil(), 0));
        this.setSalt(Math.max(this.getSalt() - cuisineIngredientCategoryWeightVo.getSalt(), 0));
        this.setCalorie(Math.max(this.getCalorie() - calorie, 0));
    }

    private static final long serialVersionUID = 1L;
}