package com.nutrition.nutritionservice.vo.store;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * cuisine
 * @author heng.liu
 * @since 2020/12/25
 */
@Data
@Builder
public class CuisineVo implements Serializable {
    private int id;

    /**
     * 菜品编码
     */
    private String code;

    /**
     * 菜品名称
     */
    private String name;

    /**
     * 价格
     */
    private double price;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 菜品热量
     */
    private double calorie;

    /**
     * 推荐目标
     */
    private int goal;

    /**
     * 菜品种类
     */
    private int cuisineType;

    /**
     * 用餐时段
     */
    private int dineTime;

    /**
     * 菜温
     */
    private int warm;

    /**
     * 菜品状态
     */
    private int status;

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