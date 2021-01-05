package com.nutrition.nutritionservice.vo.user;

import com.nutrition.nutritionservice.enums.database.UserAccountStatusTypeEnum;
import com.nutrition.nutritionservice.vo.TimeBasedVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 用户账号信息。
 * 
 * @author heng.liu
 * @since 2020/9/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserAccountVo extends TimeBasedVo implements UserDetails {

    private static final long serialVersionUID = -1106127018250012296L;

    private String uuid;

    private int type;

    private String externalId;

    private int status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return "password";
    }

    @Override
    public String getUsername() {
        return uuid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status != UserAccountStatusTypeEnum.EXPIRE.getCode();
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != UserAccountStatusTypeEnum.LOCKED.getCode();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == UserAccountStatusTypeEnum.ENABLE.getCode();
    }
}
