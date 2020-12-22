package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.user.UserAccountVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * 用户账号Mapper。
 * 
 * @author heng.liu
 * @since 2020/9/23
 */
public interface UserAccountDao {

    @Select("select * from user_account where uuid = #{uuid}")
    UserAccountVo selectByUuid(String uuid);

    @Select("select * from user_account where external_id = #{externalId} and type = #{type}")
    UserAccountVo selectByExternalIdAndType(String externalId, int type);

    @Insert("insert into user_account(uuid, password, type, external_id, status) values (#{uuid}, #{password}, #{type}, #{externalId}, #{status})")
    int insert(UserAccountVo userAccount);

}
