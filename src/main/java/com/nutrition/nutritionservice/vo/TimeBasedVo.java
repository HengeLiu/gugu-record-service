package com.nutrition.nutritionservice.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 基于时间的值对象。
 *
 * @author heng.liu
 * @since 2020/9/21
 */
@Data
public class TimeBasedVo {

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
