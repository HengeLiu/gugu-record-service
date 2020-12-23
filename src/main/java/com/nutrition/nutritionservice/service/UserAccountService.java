package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.enums.database.UserAccountTypeEnum;
import com.nutrition.nutritionservice.dao.UserAccountDao;
import com.nutrition.nutritionservice.vo.user.UserAccountVo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author heng.liu
 * @since 2020/9/23
 */
@Service
public class UserAccountService implements UserDetailsService {

    @Resource
    private UserAccountDao userAccountDao;

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        return userAccountDao.selectByUuid(uuid);
    }

    public UserAccountVo queryByExternalIdAndType(String externalId, UserAccountTypeEnum typeEnum) {
        return userAccountDao.selectByExternalIdAndType(externalId, typeEnum.getCode());
    }

    public boolean addUserAccount(UserAccountVo userAccountVo) {
        return userAccountDao.insert(userAccountVo) == 1;
    }

}
