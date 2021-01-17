package com.nutrition.nutritionservice.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * store_info
 * 
 * @author heng.liu
 */
@Data
public class StoreInfoVo implements Serializable {
    private Long id;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 门店地址名称
     */
    private String locationTitle;

    /**
     * 场地类型
     */
    private Byte locationType;

    /**
     * 位置经度
     */
    private Double locationLatitude;

    /**
     * 位置维度
     */
    private Double locationLongitude;

    /**
     * 业务类型
     */
    private Byte businessType;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}