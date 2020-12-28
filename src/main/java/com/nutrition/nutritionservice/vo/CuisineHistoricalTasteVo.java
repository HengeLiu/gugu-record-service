package com.nutrition.nutritionservice.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * cuisine_historical_taste
 * @author heng.liu
 * @since 2020/12/28
 */
@Data
public class CuisineHistoricalTasteVo implements Serializable {
    private Integer id;

    /**
     * 菜品编码
     */
    private String cuisineCode;

    /**
     * 口味评价
     */
    private byte taste;

    /**
     * 发生次数
     */
    private int count;


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