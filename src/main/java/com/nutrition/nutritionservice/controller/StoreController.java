package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.StoreBiz;
import com.nutrition.nutritionservice.common.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 门店
 *
 * @author heng.liu
 * @since 2021/1/12
 */
@RestController
@RequestMapping("/store")
public class StoreController {

    @Resource
    private StoreBiz storeBiz;

    @GetMapping("/query-store-list")
    public Response queryStoreList() {
        return Response.success(storeBiz.queryAllStore());
    }

}
