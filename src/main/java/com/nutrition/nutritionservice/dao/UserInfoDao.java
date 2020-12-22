package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * 用户信息Mapper。
 * 
 * @author heng.liu
 * @since 2020/9/28
 */
public interface UserInfoDao {

    @Select("select * from user_info where uuid = #{uuid}")
    UserInfoVo selectByUuid(String uuid);

    @Insert("insert into user_info(uuid, gender, calorie, goal, nickname, age, height, weight, profe_char, sports_habits, info_status) "
            + "values (#{uuid}, #{gender}, #{calorie}, #{goal}, #{nickname}, #{age}, #{height}, #{weight}, #{profeChar}, #{sportsHabits}, #{infoStatus}) on duplicate key update "
            + "gender = #{gender}, calorie = #{calorie}, goal = #{goal}, nickname = #{nickname},  age = #{age}, height = #{height}, weight = #{weight}, profe_char = #{profeChar}, sports_habits = #{sportsHabits}, info_status = #{infoStatus}")
    int insertOrUpdate(UserInfoVo userInfo);

}
