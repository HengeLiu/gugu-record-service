package com.nutrition.nutritionservice.vo.store;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * cuisine
 * @author 
 */
@Data
public class CuisineVo implements Serializable {
    private Integer id;

    /**
     * 菜品编码
     */
    private String code;

    /**
     * 菜品名称
     */
    private String name;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 菜品热量
     */
    private Integer calorie;

    /**
     * 推荐目标
     */
    private Byte goal;

    /**
     * 菜品种类
     */
    private Byte cuisineType;

    /**
     * 用餐时段
     */
    private Byte dineTime;

    /**
     * 菜温
     */
    private Byte warm;

    /**
     * 0.正常;1.无货;2.暂停售卖;3.下架
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}