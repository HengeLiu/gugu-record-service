package com.nutrition.nutritionservice.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * store
 * 
 * @author
 */
@Data
public class StoreVo implements Serializable {
    private Integer id;

    /**
     * 门店编码
     */
    private String code;

    /**
     * 店名
     */
    private String name;

    /**
     * 门店账号
     */
    private String managerAccountUuid;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}