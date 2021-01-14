package com.nutrition.nutritionservice.controller.health.ao;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 一页数据
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
public class PageDataAo<T> implements Serializable {
    private static final long serialVersionUID = 8143665588299216446L;

    /**
     * 当前返回页码
     */
    private Integer pageNumber;

    /**
     * 查询到的餐品总条数
     */
    private Integer rowNumber;

    /**
     * 餐品列表
     */
    private List<T> dataList;

}
