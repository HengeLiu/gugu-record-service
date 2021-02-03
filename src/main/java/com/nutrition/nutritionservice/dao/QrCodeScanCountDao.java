package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.QrCodeScanCountVo;

public interface QrCodeScanCountDao {
    int deleteByPrimaryKey(Long id);

    int insert(QrCodeScanCountVo record);

    int insertSelective(QrCodeScanCountVo record);

    QrCodeScanCountVo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QrCodeScanCountVo record);

    int updateByPrimaryKey(QrCodeScanCountVo record);
}