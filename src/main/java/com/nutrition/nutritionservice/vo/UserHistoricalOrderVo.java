package com.nutrition.nutritionservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * user_historical_order
 * 
 * @author heng.liu
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserHistoricalOrderVo extends HistoricalCuisineRecordVo implements Serializable {

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}