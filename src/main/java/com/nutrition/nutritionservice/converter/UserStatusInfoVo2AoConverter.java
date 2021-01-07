package com.nutrition.nutritionservice.converter;

import com.nutrition.nutritionservice.controller.ao.UserStatusInfoAo;
import com.nutrition.nutritionservice.vo.UserStatusInfoVo;

/**
 * @author heng.liu
 * @since 2021/1/6
 */
public class UserStatusInfoVo2AoConverter extends BaseConverter<UserStatusInfoVo, UserStatusInfoAo> {
    public static final UserStatusInfoVo2AoConverter INSTANCE = new UserStatusInfoVo2AoConverter();

    private UserStatusInfoVo2AoConverter() {
    }

    @Override
    protected UserStatusInfoAo doForward(UserStatusInfoVo userStatusInfoVo) {
        UserStatusInfoAo userStatusInfoAo = new UserStatusInfoAo();
        userStatusInfoAo.setCustomInfo(userStatusInfoVo.getCustomInfo());
        userStatusInfoAo.setShownInfoCollectWindow(userStatusInfoVo.getShownInfoCollectWindow());
        userStatusInfoAo.setShownProcessWindow(userStatusInfoVo.getShownProcessWindow());
        return userStatusInfoAo;
    }

    @Override
    protected UserStatusInfoVo doBackward(UserStatusInfoAo userStatusInfoAo) {
        throw new UnsupportedOperationException("UserStatusInfoVo2AoConverter.doBackward");
    }
}
