package com.nutrition.nutritionservice.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 按照ID分页参数对象。
 * <P>
 * 注意：不能保证每页数据条数一定为指定值，根据分页大小，可能会存在个别误差。
 * 
 * @author heng.liu
 * @since 2020/12/28
 */
@Data
@Builder
public class IDPageParamVo {

    /**
     * 当前页数。
     */
    private int pageNumber;

    /**
     * 每页数据条数。
     */
    private int rowNumber;

    public int getFirstRowNumber() {
        return pageNumber > 0 ? (pageNumber - 1) * rowNumber : 0;
    }

}
