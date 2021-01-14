package com.nutrition.nutritionservice.controller.health.ao;

import com.nutrition.nutritionservice.vo.ModelParamVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoAo extends ModelParamVo implements Serializable {
    private static final long serialVersionUID = 2791927152656988438L;

    /**
     * 系统用户唯一标识
     */
    private String uuid;

    /**
     * 是否点击了“跳过”
     */
    private boolean skip;

}
