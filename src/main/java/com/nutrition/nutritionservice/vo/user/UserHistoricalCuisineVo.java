package com.nutrition.nutritionservice.vo.user;

import com.nutrition.nutritionservice.vo.HistoricalCuisineRecordVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * user_historical_cuisine
 * 
 * @author heng.liu
 * @since 2020/12/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserHistoricalCuisineVo extends HistoricalCuisineRecordVo implements Serializable {

    /**
     * 味道评分
     */
    private int tasteScore;

    /**
     * 是否是自定义的记录 {@link com.nutrition.nutritionservice.enums.BooleanEnum}
     */
    private int custom;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}