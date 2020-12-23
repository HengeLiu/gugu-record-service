package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.enums.database.GenderEnum;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * 配置项服务。
 *
 * @author heng.liu
 * @since 2020/9/21
 */
@Service
@PropertySource({"classpath:business-config.properties","classpath:system-config.properties"})
@Getter
public class ConfigPropertiesService {

    @Value("${costumer_default_goal_female}")
    private int costumerDefaultGoalFemale;
    @Value("${costumer_default_goal_male}")
    private int costumerDefaultGoalMale;
    @Value("${costumer_default_age_female}")
    private int costumerDefaultAgeFemale;
    @Value("${costumer_default_age_male}")
    private int costumerDefaultAgeMale;
    @Value("${costumer_default_height_female}")
    private int costumerDefaultHeightFemale;
    @Value("${costumer_default_height_male}")
    private int costumerDefaultHeightMale;
    @Value("${costumer_default_weight_female}")
    private int costumerDefaultWeightFemale;
    @Value("${costumer_default_weight_male}")
    private int costumerDefaultWeightMale;
    @Value("${costumer_default_profe_char_female}")
    private int costumerDefaultProfeCharFemale;
    @Value("${costumer_default_profe_char_male}")
    private int costumerDefaultProfeCharMale;
    @Value("${costumer_default_sports_habits_female}")
    private int costumerDefaultSportsHabitsFemale;
    @Value("${costumer_default_sports_habits_male}")
    private int costumerDefaultSportsHabitsMale;

    public UserInfoVo getDefaultUserInfo(GenderEnum genderEnum) {
        if (genderEnum == null || genderEnum == GenderEnum.FEMALE) {
            return UserInfoVo.builder().gender(GenderEnum.FEMALE.getCode()).goal(costumerDefaultGoalFemale)
                    .age(costumerDefaultAgeFemale).height(costumerDefaultHeightFemale)
                    .weight(costumerDefaultWeightFemale).profeChar(costumerDefaultProfeCharFemale)
                    .sportsHabits(costumerDefaultSportsHabitsFemale).build();

        } else {
            return UserInfoVo.builder().goal(costumerDefaultGoalMale).age(costumerDefaultAgeMale)
                    .height(costumerDefaultHeightMale).weight(costumerDefaultWeightMale)
                    .profeChar(costumerDefaultProfeCharMale).sportsHabits(costumerDefaultSportsHabitsMale).build();
        }
    }


}
