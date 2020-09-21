package com.nutrition.nutritionservice.vo.store;

import com.nutrition.nutritionservice.vo.TimeBasedVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 门店Vo。
 * 
 * @author heng.liu
 * @since 2020/9/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StoreVo extends TimeBasedVo {

    private String code;

    private String creditCode;

}
