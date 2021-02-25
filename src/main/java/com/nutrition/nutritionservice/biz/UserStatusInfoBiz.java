package com.nutrition.nutritionservice.biz;

import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.service.UserStatusInfoService;
import com.nutrition.nutritionservice.vo.UserStatusInfoVo;

import javax.annotation.Resource;

/**
 * @author heng.liu
 * @since 2021/2/25
 */
@Biz
public class UserStatusInfoBiz {

    @Resource
    private UserStatusInfoService userStatusInfoService;

    public void notNotifyOrderHelper(String uuid) {
        userStatusInfoService.updateByUuidSelective(UserStatusInfoVo.builder().showProcessWindow(0).uuid(uuid).build());
    }

    public UserStatusInfoVo query(String uuid) {
        return userStatusInfoService.queryBuUuid(uuid);
    }

}
