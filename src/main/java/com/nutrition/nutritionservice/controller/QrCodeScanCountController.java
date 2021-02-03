package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.biz.QrCodeScanCountBiz;
import com.nutrition.nutritionservice.common.Response;
import com.nutrition.nutritionservice.vo.QrCodeScanCountVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author heng.liu
 * @since 2021/2/3
 */
@Controller
@RequestMapping("/qr-code")
public class QrCodeScanCountController {

    @Resource
    private QrCodeScanCountBiz qrCodeScanCountBiz;

    @RequestMapping("/count")
    @ResponseBody
    public Response count(@RequestBody QrCodeScanCountVo qrCodeScanCountVo) {
        qrCodeScanCountBiz.add(qrCodeScanCountVo);
        return Response.success();
    }

}
