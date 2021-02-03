package com.nutrition.nutritionservice.biz;

import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.service.QrCodeScanCountService;
import com.nutrition.nutritionservice.vo.QrCodeScanCountVo;

import javax.annotation.Resource;

/**
 * @author heng.liu
 * @since 2021/2/3
 */
@Biz
public class QrCodeScanCountBiz {

    @Resource
    private QrCodeScanCountService qrCodeScanCountService;

    public void add(QrCodeScanCountVo qrCodeScanCountVo) {
        qrCodeScanCountService.add(qrCodeScanCountVo);
    }

}
