package com.nutrition.nutritionservice.controller.health.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户设置
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSettingsAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 点餐平台
     */
    private Byte defaultTakeawayPlatformCode;

    /**
     * 用户默认点餐位置
     */
    private LocationAo defaultOrderLocation;

}
