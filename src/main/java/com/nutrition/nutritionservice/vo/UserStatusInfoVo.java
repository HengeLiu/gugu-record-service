package com.nutrition.nutritionservice.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user_status_info
 * 
 * @author heng.liu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusInfoVo implements Serializable {
    private long id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 是否已完成信息录入
     */
    private int customInfo;

    /**
     * 是否已展示「健康信息收集」浮窗
     */
    private int shownInfoCollectWindow;

    /**
     * 是否已展示「使用流程」浮窗
     */
    private int shownProcessWindow;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}