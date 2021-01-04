package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * 位置信息
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
public class LocationAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 位置名称
     */
    private String title;

    /**
     * 经度
     */
    private Double latitude;

    /**
     * 维度
     */
    private Double longitude;

}
