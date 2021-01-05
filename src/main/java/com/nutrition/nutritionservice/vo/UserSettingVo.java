package com.nutrition.nutritionservice.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * user_setting
 * 
 * @author heng.liu
 */
@Data
public class UserSettingVo implements Serializable {
    private long id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 默认点餐外卖平台类型
     */
    @Nullable
    private Byte takeawayCode;

    /**
     * 默认点餐位置
     */
    @Nullable
    private Long locationId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}