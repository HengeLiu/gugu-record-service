package com.nutrition.nutritionservice.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * qr_code_scan_count
 * @author heng.liu
 */
@Data
public class QrCodeScanCountVo implements Serializable {
    private Long id;

    /**
     * 二维码批次号
     */
    private String batchVersion;

    /**
     * 扫码用户uuid
     */
    private String uuid;

    /**
     * 二维码内容
     */
    private String content;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}