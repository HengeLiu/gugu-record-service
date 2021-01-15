package com.nutrition.nutritionservice.controller.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户状态信息
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Data
public class UserStatusInfoAo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否已完成信息录入
     */
    private Integer customInfo;

    /**
     * 是否已展示「健康信息收集」浮窗
     */
    private Integer shownInfoCollectWindow;

    /**
     * 是否已展示「使用流程」浮窗
     */
    private Integer shownProcessWindow;

}
