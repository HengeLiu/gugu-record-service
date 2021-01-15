package com.nutrition.nutritionservice.controller.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户建议
 *
 * @author heng.liu
 * @since 2021/1/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOpinionAo implements Serializable {

    private static final long serialVersionUID = 4769999989741362843L;

    /**
     * 用户uuid
     */
    private String uuid;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 建议内容
     */
    private String content;

}
