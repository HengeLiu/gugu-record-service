package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.EleManageBiz;
import com.nutrition.nutritionservice.common.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 饿了么店铺管理
 *
 * @author heng.liu
 * @since 2021/6/16
 */
@RestController
@RequestMapping("/store-manage/ele")
public class EleManageController {

    @Resource
    private EleManageBiz eleManageBiz;

    public Response test(){
        return Response.success();
    }

}
