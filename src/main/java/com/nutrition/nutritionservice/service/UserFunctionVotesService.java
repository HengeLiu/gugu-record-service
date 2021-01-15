package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.UserFunctionVotesDao;
import com.nutrition.nutritionservice.vo.UserFunctionVotesVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author heng.liu
 * @since 2021/1/15
 */
@Service
public class UserFunctionVotesService {

    @Resource
    private UserFunctionVotesDao userFunctionVotesDao;

    public int add(UserFunctionVotesVo userFunctionVotesVo) {
        return userFunctionVotesDao.insert(userFunctionVotesVo);
    }

    public List<UserFunctionVotesVo> queryByUuidAndDate(String uuid, LocalDate localDate) {
        return userFunctionVotesDao.selectByUuidAndDateTime(uuid, localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX));
    }

}
