package com.nutrition.nutritionservice.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * product_function
 * 
 * @author heng.liu
 */
@Data
public class ProductFunctionVo implements Serializable {
    private Integer id;

    /**
     * 功能编码
     */
    private String code;

    /**
     * 功能标题
     */
    private String title;

    /**
     * 功能介绍
     */
    private String introduction;

    /**
     * 功能状态
     */
    private Byte status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}