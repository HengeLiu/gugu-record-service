package com.nutrition.nutritionservice.vo.user;

import com.nutrition.nutritionservice.vo.CuisineCategoryWeightVo;
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
 * @since 20.020.0/12/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserIngredientWeightSumDailyVo extends CategoryModel<Double> implements Serializable {
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
        emptyVo.setProcessedGrains(0.0);
        emptyVo.setUnprocessedGrains(0.0);
        emptyVo.setMixedBeans(0.0);
        emptyVo.setTuber(0.0);
        emptyVo.setGeneralVegetables(0.0);
        emptyVo.setDarkVegetables(0.0);
        emptyVo.setFruit(0.0);
        emptyVo.setMeat(0.0);
        emptyVo.setPoultry(0.0);
        emptyVo.setAquatic(0.0);
        emptyVo.setEgg(0.0);
        emptyVo.setDairy(0.0);
        emptyVo.setSoybean(0.0);
        emptyVo.setNut(0.0);
        emptyVo.setOil(0.0);
        emptyVo.setSalt(0.0);
        emptyVo.setUuid(uuid);
        emptyVo.setDate(date);
        emptyVo.setCalorie(0.0);
        return emptyVo;
    }

    public void addCuisineCategoryWeight(CuisineCategoryWeightVo cuisineCategoryWeightVo) {
        this.setProcessedGrains(this.getProcessedGrains() + cuisineCategoryWeightVo.getProcessedGrains());
        this.setUnprocessedGrains(this.getUnprocessedGrains() + cuisineCategoryWeightVo.getUnprocessedGrains());
        this.setMixedBeans(this.getMixedBeans() + cuisineCategoryWeightVo.getMixedBeans());
        this.setTuber(this.getTuber() + cuisineCategoryWeightVo.getTuber());
        this.setGeneralVegetables(this.getGeneralVegetables() + cuisineCategoryWeightVo.getGeneralVegetables());
        this.setDarkVegetables(this.getDarkVegetables() + cuisineCategoryWeightVo.getDarkVegetables());
        this.setFruit(this.getFruit() + cuisineCategoryWeightVo.getFruit());
        this.setMeat(this.getMeat() + cuisineCategoryWeightVo.getMeat());
        this.setPoultry(this.getPoultry() + cuisineCategoryWeightVo.getPoultry());
        this.setAquatic(this.getAquatic() + cuisineCategoryWeightVo.getAquatic());
        this.setEgg(this.getEgg() + cuisineCategoryWeightVo.getEgg());
        this.setDairy(this.getDairy() + cuisineCategoryWeightVo.getDairy());
        this.setSoybean(this.getSoybean() + cuisineCategoryWeightVo.getSoybean());
        this.setNut(this.getNut() + cuisineCategoryWeightVo.getNut());
        this.setOil(this.getOil() + cuisineCategoryWeightVo.getOil());
        this.setSalt(this.getSalt() + cuisineCategoryWeightVo.getSalt());
        this.setCalorie(this.getCalorie() + cuisineCategoryWeightVo.getCalorie());
    }

    private static final long serialVersionUID = 1L;
}