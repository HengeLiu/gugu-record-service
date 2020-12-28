package com.nutrition.nutritionservice.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * user_historical_cuisine
 * 
 * @author heng.liu
 * @since 2020/12/28
 */
@Data
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
    private byte tasteScore;

    /**
     * 菜品记录状态.0,正常;1,已删除.
     */
    private byte status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}