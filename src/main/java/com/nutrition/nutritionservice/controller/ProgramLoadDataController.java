package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.ProgramLoadDataBiz;
import com.nutrition.nutritionservice.common.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 程序加载时获取预备数据。
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@RestController
@RequestMapping("/load")
public class ProgramLoadDataController {

    @Resource
    private ProgramLoadDataBiz programLoadDataBiz;

    @PostMapping("/user-info")
    public Response loadUserInfo(String openid) {
        programLoadDataBiz.loadUserInfo(openid);
        return Response.success();
    }

}
