package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜品外卖平台url
 *
 * @author heng.liu
 * @since 2021/1/11
 */
@Data
public class CuisineTakeawayPlatformUrlAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台编码
     */
    private Integer code;

    /**
     * 地址
     */
    private String url;

}
