package com.nutrition.nutritionservice.mapper;

import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * 用户信息Mapper。
 * 
 * @author heng.liu
 * @since 2020/9/28
 */
public interface UserInfoMapper {

    @Select("select * from user_info where uuid = #{uuid}")
    UserInfoVo selectByUuid(String uuid);

    @Insert("insert into user_info(uuid, nick_name, gender, age, height, weight) values (#{uuid}, #{nick_name}, #{gender}, #{age}, #{height}, #{weight}) on duplicate key update "
            + "nick_name#{nick_name}, gender = #{gender}, age = #{age}, height = #{height}, weight = #{weight}")
    int insertOrUpdate(UserInfoVo userInfo);

}
