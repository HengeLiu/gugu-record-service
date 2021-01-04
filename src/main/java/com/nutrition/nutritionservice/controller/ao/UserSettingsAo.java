package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户设置
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
public class UserSettingsAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 点餐平台
     */
    private Integer defaultTakeawayPlatformCode;

    /**
     * 用户默认点餐位置
     */
    private LocationAo defaultOrderLocation;

    /**
     * 系统默认点餐位置
     */
    private LocationAo systemDefaultOrderLocation;

}
