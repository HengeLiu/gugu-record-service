package com.nutrition.nutritionservice.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * user_historical_cuisine
 * 
 * @author heng.liu
 * @since 2020/12/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHistoricalCuisineVo implements Serializable {
    private long id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 菜品编码
     */
    private String cuisineCode;

    /**
     * 味道评分
     */
    private int tasteScore;

    /**
     * 菜品记录状态.0,正常;1,已删除.
     */
    private int status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}