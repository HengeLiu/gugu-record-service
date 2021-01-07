package com.nutrition.nutritionservice.vo.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.nutrition.nutritionservice.vo.ModelParamVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * user_info
 * 
 * @author heng.liu
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo extends ModelParamVo implements Serializable {
    private long id;

    /**
     * 用户唯一标识
     */
    private String uuid;


    /**
     * 日需热量
     */
    private Double calorie;


    /**
     * 昵称
     */
    private String nickname;

    /**
     * 当前使用的用户模型
     */
    private Long activeModelId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}