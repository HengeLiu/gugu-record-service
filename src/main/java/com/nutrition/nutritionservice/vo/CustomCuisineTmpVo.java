package com.nutrition.nutritionservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * custom_cuisine_tmp
 * 
 * @author heng.liu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomCuisineTmpVo implements Serializable {
    private Integer id;

    /**
     * 菜品编码
     */
    private String code;

    /**
     * 售价,分
     */
    private Integer price;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 菜品热量
     */
    private Double calorie;

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