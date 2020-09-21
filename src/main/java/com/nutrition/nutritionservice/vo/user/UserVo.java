package com.nutrition.nutritionservice.vo.user;

import com.nutrition.nutritionservice.vo.TimeBasedVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户。
 * 
 * @author heng.liu
 * @since 2020/9/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserVo extends TimeBasedVo {

    private String uuid;

    private String nickName;

    private int type;

    private String externalId;

}
