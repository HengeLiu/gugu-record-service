package com.nutrition.nutritionservice.controller.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户订单记录详情
 *
 * @author heng.liu
 * @since 2021/2/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderRecordDetailsAo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;

    private Long orderRecordId;

    private String createTimeStr;

    private CuisineDetailsAo cuisineDetails;

}
