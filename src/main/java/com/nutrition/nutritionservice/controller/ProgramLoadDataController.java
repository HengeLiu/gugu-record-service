package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.record.ProgramLoadDataBiz;
import com.nutrition.nutritionservice.common.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 程序加载
 *
 * @author heng.liu
 * @since 2021/1/14
 */
@RestController
@RequestMapping("/load")
public class ProgramLoadDataController {

    @Resource
    private ProgramLoadDataBiz programLoadDataBiz;

    @GetMapping("/user-info")
    public Response loadUserInfo(@RequestParam String openid) {
        return Response.success(programLoadDataBiz.loadUserInfo(openid));
    }

    @PostMapping("/user-info/by-code")
    public Response loadUserInfoByWxCode(@RequestBody String code) {
        return Response.success(programLoadDataBiz.loadUserInfoByWxCodeCode(code));
    }

}
