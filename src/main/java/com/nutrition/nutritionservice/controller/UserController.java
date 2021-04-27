package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.UserBiz;
import com.nutrition.nutritionservice.common.Response;
import com.nutrition.nutritionservice.controller.ao.UserCustomDietRecordAo;
import com.nutrition.nutritionservice.controller.ao.UserInfoAo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * @author heng.liu
 * @since 2020/9/13
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserBiz userBiz;

    @GetMapping("/query/uuid-by-code")
    public Response queryUuidByWechatCode(@RequestParam String wechatCode){
        return Response.success(userBiz.queryUuidByWechatCode(wechatCode));
    }

    @PostMapping("/save/user-info")
    public Response saveUserInfo(@RequestBody UserInfoAo userInfoVo) {
        return Response.success(userBiz.saveUserInfo(userInfoVo));
    }

    @GetMapping("/save/cuisine-history")
    public Response saveCuisineHistory(@RequestParam String uuid, @RequestParam String cuisineCode) {
        userBiz.saveCuisineHistory(uuid, cuisineCode, LocalDate.now());
        return Response.success();
    }

    @PostMapping("/save/custom-cuisine-history")
    public Response saveCustomCuisineHistory(@RequestBody UserCustomDietRecordAo userCustomDietRecordAo) {
        userBiz.saveCustomCuisineHistory(userCustomDietRecordAo, LocalDate.now());
        return Response.success();
    }

    @PostMapping("/update/custom-cuisine-history")
    public Response updateCustomCuisineHistory(@RequestBody UserCustomDietRecordAo userCustomDietRecordAo) {
        userBiz.updateCustomCuisineHistory(userCustomDietRecordAo);
        return Response.success();
    }

    @GetMapping("/remove/cuisine-history")
    public Response removeCuisineHistory(@RequestParam String uuid, @RequestParam Long userHistoricalCuisineId) {
        userBiz.removeDietRecord(uuid, userHistoricalCuisineId);
        return Response.success();
    }

    @GetMapping("/query/user-diet-record-details")
    public Response queryCuisineHistory(@RequestParam Long userHistoricalCuisineId) {
        return Response.success(userBiz.queryUserDietRecordDetails(userHistoricalCuisineId));
    }

    @GetMapping("/query/today-cuisine-history")
    public Response queryUserDietRecords(@RequestParam String uuid) {
        return Response.success(userBiz.queryUserDietRecords(uuid));
    }

    @GetMapping("/query/user-info")
    public Response queryUserInfo(@RequestParam String uuid) {
        return Response.success(userBiz.queryUserInfo(uuid));
    }

}
