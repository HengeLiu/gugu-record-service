package com.nutrition.nutritionservice.controller.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户饮食记录
 *
 * @author heng.liu
 * @since 2021/2/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDietRecordDetailsAo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;

    private Long dietRecordId;

    private UserCustomDietRecordAo customDietRecord;

    private CuisineDetailsAo cuisineDetails;

}
