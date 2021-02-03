package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.QrCodeScanCountDao;
import com.nutrition.nutritionservice.vo.QrCodeScanCountVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author heng.liu
 * @since 2021/2/3
 */
@Service
public class QrCodeScanCountService {

    @Resource
    private QrCodeScanCountDao qrCodeScanCountDao;

    @Transactional(rollbackFor = Exception.class)
    public void add(QrCodeScanCountVo qrCodeScanCountVo) {
        qrCodeScanCountDao.insert(qrCodeScanCountVo);
    }

}
