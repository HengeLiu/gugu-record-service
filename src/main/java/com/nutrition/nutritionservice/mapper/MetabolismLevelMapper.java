package com.nutrition.nutritionservice.mapper;

import com.nutrition.nutritionservice.vo.modeldata.ModelMetabolismLevelVo;
import org.apache.ibatis.annotations.Select;

/**
 * 代谢水平Mapper。
 *
 * @author heng.liu
 * @since 2020/9/20
 */
public interface MetabolismLevelMapper {

    @Select("select * from model_metabolism_level where gender = #{gender} and age >= #{age} order by age limit 1")
    ModelMetabolismLevelVo select(int gender, int age);

}
