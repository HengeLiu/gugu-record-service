package com.nutrition.nutritionservice.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * user_location
 * 
 * @author
 */
@Data
public class UserLocationVo implements Serializable {
    private long id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 地址名称
     */
    private String title;

    /**
     * 经度
     */
    private double latitude;

    /**
     * 维度
     */
    private double longitude;

    /**
     * 地址类型
     */
    private byte locationType;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 用户自定义名称
     */
    private String name;

    private static final long serialVersionUID = 1L;
}