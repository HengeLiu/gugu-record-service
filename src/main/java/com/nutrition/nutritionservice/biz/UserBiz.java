package com.nutrition.nutritionservice.biz;

import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.enums.database.UserAccountStatusTypeEnum;
import com.nutrition.nutritionservice.enums.database.UserAccountTypeEnum;
import com.nutrition.nutritionservice.exception.NutritionServiceException;
import com.nutrition.nutritionservice.service.EnergyCalorieCalculateService;
import com.nutrition.nutritionservice.service.UserAccountService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.service.WechatHttpApiService;
import com.nutrition.nutritionservice.util.UUIDUtils;
import com.nutrition.nutritionservice.vo.user.UserAccountVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
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

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private EnergyCalorieCalculateService energyCalorieCalculateService;

    private UserInfoVo queryUserInfo(String uuid) {
        return userInfoService.selectByUuid(uuid);
    }

    public UserInfoVo loginWithWechat(String jsCode) {
        String wxOpenid = wechatHttpApiService.getUserOpenId(jsCode);
        if (StringUtils.isEmpty(wxOpenid)) {
            log.error("Cannot get wx.openid.");
            throw new NutritionServiceException("未查询到用户的微信账号");
        }
        UserAccountVo userAccount = userAccountService.queryByExternalIdAndType(wxOpenid, UserAccountTypeEnum.WEI_XIN);
        if (userAccount == null) {
            String uuid = UUIDUtils.createRandomUUID();
            userAccount = new UserAccountVo();
            userAccount.setUuid(uuid);
            userAccount.setType(UserAccountTypeEnum.WEI_XIN.getCode());
            userAccount.setExternalId(wxOpenid);
            userAccount.setStatus(UserAccountStatusTypeEnum.ENABLE.getCode());
            register(userAccount);
        }
        UserInfoVo userInfo = userInfoService.selectByUuid(userAccount.getUuid());
        if (userInfo == null) {
            userInfo = UserInfoVo.builder().uuid(userAccount.getUuid()).build();
        }
        return userInfo;
    }

    public void register(UserAccountVo userAccount) {
        if (userAccount.getPassword() == null) {
//            userAccount.setPassword("");
        }
        userAccountService.addUserAccount(userAccount);
    }

    public void saveUserInfo(UserInfoVo userInfoVo) {
        double calorie = energyCalorieCalculateService.calculateByUserInfo(userInfoVo);
        userInfoVo.setCalorie(calorie);
        userInfoService.add(userInfoVo);
    }

    public int calculateAndSaveUserCalorie(String uuid) {
        UserInfoVo userInfoVo = userInfoService.selectByUuid(uuid);
        int calorie = energyCalorieCalculateService.calculateByUserInfo(userInfoVo);
        userInfoVo.setCalorie((double) calorie);
        userInfoService.add(userInfoVo);
        return calorie;
    }

}
