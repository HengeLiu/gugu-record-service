package com.nutrition.nutritionservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 历史记录
 *
 * @author heng.liu
 * @since 2021/2/24
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalCuisineRecordVo {

    private long id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 菜品编码
     */
    private String cuisineCode;

    /**
     * 记录状态.0,正常;1,已删除.
     */
    private int status;

    private LocalDateTime createTime;

}
