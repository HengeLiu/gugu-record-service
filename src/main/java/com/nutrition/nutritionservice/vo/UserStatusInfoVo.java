package com.nutrition.nutritionservice.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user_status_info
 * @author 
 */
@Data
public class UserStatusInfoVo implements Serializable {
    private Long id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 是否已完成信息录入
     */
    private Byte customInfo;

    /**
     * 是否已展示「健康信息收集」浮窗
     */
    private Byte shownInfoCollectWindow;

    /**
     * 是否已展示「使用流程」浮窗
     */
    private Byte shownProcessWindow;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}