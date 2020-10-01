package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.mapper.UserIntakeCategoryModelMapper;
import com.nutrition.nutritionservice.vo.user.UserIntakeCategoryModelVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author heng.liu
 * @since 2020/9/29
 */
@Service
public class UserIntakeCategoryModelService {

    @Resource
    private UserIntakeCategoryModelMapper userIntakeCategoryModelMapper;

    public int batchInsert(List<UserIntakeCategoryModelVo> userIntakeCategoryModelVoList) {
        return 0;
    }

    public int batchUpdate(List<UserIntakeCategoryModelVo> userIntakeCategoryModelVoList) {
        return 0;
    }

}
