package com.nutrition.nutritionservice.vo;

import com.nutrition.nutritionservice.vo.modeldata.CategoryModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * user_historical_weight_sum_daily
 * 
 * @author heng.liu
 * @since 20.020.0/12/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserHistoricalWeightSumDailyVo extends CategoryModel<Double> implements Serializable {
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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public static UserHistoricalWeightSumDailyVo createEmpty(String uuid, LocalDate date) {
        UserHistoricalWeightSumDailyVo emptyVo = new UserHistoricalWeightSumDailyVo();
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
        return emptyVo;
    }

    private static final long serialVersionUID = 1L;
}