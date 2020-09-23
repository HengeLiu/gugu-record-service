package com.nutrition.nutritionservice.biz;

import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.enums.UserAccountStatusTypeEnum;
import com.nutrition.nutritionservice.enums.UserAccountTypeEnum;
import com.nutrition.nutritionservice.service.UserAccountService;
import com.nutrition.nutritionservice.service.WechatHttpApiService;
import com.nutrition.nutritionservice.util.UUIDUtils;
import com.nutrition.nutritionservice.vo.user.UserAccountVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 用户业务层。
 *
 * @author heng.liu
 * @since 2020/9/21
 */
@Biz
@Slf4j
public class UserBiz {

    @Resource
    private WechatHttpApiService wechatHttpApiService;

    @Resource
    private UserAccountService userAccountService;

    public String loginWithWechat(String jsCode) {
        String wxOpenid = wechatHttpApiService.getUserOpenId(jsCode);
        if (StringUtils.isEmpty(wxOpenid)) {
            log.error("Cannot get wx.openid.");
            return "";
        }
        UserAccountVo userAccount = userAccountService.queryByExternalIdAndType(wxOpenid, UserAccountTypeEnum.WEI_XIN);
        if (userAccount == null) {
            String uuid = UUIDUtils.createRandomUUID();
            userAccount = new UserAccountVo();
            userAccount.setUuid(uuid);
            userAccount.setType(UserAccountTypeEnum.WEI_XIN.getCode());
            userAccount.setExternalId(wxOpenid);
            userAccount.setStatus(UserAccountStatusTypeEnum.ENABLE.getCode());
            if (register(userAccount)) {
                return uuid;
            }
        } else {
            return userAccount.getUuid();
        }
        return "";
    }

    public boolean register(UserAccountVo userAccount) {
        if (userAccount.getPassword() == null) {
            userAccount.setPassword("");
        }
        return userAccountService.addUserAccount(userAccount);
    }

}
