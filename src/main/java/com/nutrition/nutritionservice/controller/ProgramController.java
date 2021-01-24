package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.ProgramBiz;
import com.nutrition.nutritionservice.common.Response;
import com.nutrition.nutritionservice.controller.ao.StoreRecommendAo;
import com.nutrition.nutritionservice.controller.ao.UserOpinionAo;
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
@RequestMapping("/product")
public class ProgramController {

    @Resource
    private ProgramBiz programBiz;

    @GetMapping("/load/user-info")
    public Response loadUserInfo(@RequestParam String openid) {
        return Response.success(programBiz.loadUserInfo(openid));
    }

    @GetMapping("/load/user-info/by-code")
    public Response loadUserInfoByWxCode(@RequestParam String code) {
        return Response.success(programBiz.loadUserInfoByWxCode(code));
    }

    @GetMapping("/function-list")
    public Response queryFunctionList(@RequestParam String uuid) {
        return Response.success(programBiz.queryNominatedFunctionList(uuid));
    }

    /**
     * 用户催更
     */
    @GetMapping("/push-function")
    public Response pushFunction(@RequestParam String uuid, @RequestParam String functionCode) {
        programBiz.pushingFunction(uuid, functionCode);
        return Response.success();
    }

    /**
     * 用户提交建议
     * @param userOpinionAo 用户建议
     */
    @PostMapping("/express-opinion")
    public Response expressOpinion(@RequestBody UserOpinionAo userOpinionAo) {
        programBiz.saveOpinion(userOpinionAo);
        return Response.success();
    }

    @PostMapping("/recommend-store")
    public Response recommendStore(@RequestBody StoreRecommendAo storeRecommendAo) {
        programBiz.saveStoreRecommendation(storeRecommendAo);
        return Response.success();
    }

}
