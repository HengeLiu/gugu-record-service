package com.nutrition.nutritionservice.controller.ao;

import com.nutrition.nutritionservice.vo.ProductFunctionVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 功能列表
 *
 * @author heng.liu
 * @since 2021/1/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFunctionListAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户今日内上次催更功能编码
     */
    private String userTodayLastPushingCode;

    /**
     * 用户今日催更次数
     */
    private Integer userTodayPushingTime;

    /**
     * 功能列表
     */
    private List<ProductFunctionVo> productFunctionList;

}
