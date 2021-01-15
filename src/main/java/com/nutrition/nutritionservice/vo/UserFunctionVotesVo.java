package com.nutrition.nutritionservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * user_function_votes
 * 
 * @author heng.liu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFunctionVotesVo implements Serializable {
    private Integer id;

    /**
     * 用户uuid
     */
    private String uuid;

    /**
     * 功能编码
     */
    private String functionCode;

    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}