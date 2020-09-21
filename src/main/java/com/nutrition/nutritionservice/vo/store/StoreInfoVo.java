package com.nutrition.nutritionservice.vo.store;

import com.nutrition.nutritionservice.vo.TimeBasedVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 门店信息Vo。
 * 
 * @author heng.liu
 * @since 2020/9/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StoreInfoVo extends TimeBasedVo {

    private String storeCode;

    private String name;

    private String address;

    private int locationType;

    private int businessType;

}
