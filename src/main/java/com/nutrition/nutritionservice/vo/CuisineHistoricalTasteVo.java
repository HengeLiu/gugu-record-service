package com.nutrition.nutritionservice.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * cuisine_historical_taste
 * @author 
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
    private Byte tast;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}