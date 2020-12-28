package com.nutrition.nutritionservice.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.nutrition.nutritionservice.vo.modeldata.CategoryModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * user_historical_weight_sum_daily
 * 
 * @author heng.liu
 * @since 2020/12/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserHistoricalWeightSumDailyVo extends CategoryModel<Integer> implements Serializable {
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

    private static final long serialVersionUID = 1L;
}