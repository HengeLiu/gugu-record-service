package com.nutrition.nutritionservice.mapper;

import com.nutrition.nutritionservice.vo.user.UserAccountVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * 用户账号Mapper。
 * 
 * @author heng.liu
 * @since 2020/9/23
 */
public interface UserAccountMapper {

    @Select("select * from user_account where uuid = #{uuid}")
    UserAccountVo selectByUuid(String uuid);

    @Select("select * from user_account where externalId = #{externalId} and type = #{type}")
    UserAccountVo selectByExternalIdAndType(String externalId, int type);

    @Insert("insert into table user_account(uuid, password, type, external_id, status) values (#{userAccount.uuid}, #{userAccount.password}, #{userAccount.type}, #{userAccount.external_id}, #{userAccount.status})")
    int insert(UserAccountVo userAccount);

}
