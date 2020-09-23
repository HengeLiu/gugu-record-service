package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.enums.UserAccountTypeEnum;
import com.nutrition.nutritionservice.mapper.UserAccountMapper;
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
    private UserAccountMapper userAccountMapper;

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        return userAccountMapper.selectByUuid(uuid);
    }

    public UserAccountVo queryByExternalIdAndType(String externalId, UserAccountTypeEnum typeEnum) {
        return userAccountMapper.selectByExternalIdAndType(externalId, typeEnum.getCode());
    }

    public boolean addUserAccount(UserAccountVo userAccountVo) {
        return userAccountMapper.insert(userAccountVo) == 1;
    }

}
