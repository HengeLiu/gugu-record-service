package com.nutrition.nutritionservice.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间工具
 *
 * @author heng.liu
 * @since 2021/1/6
 */
@Slf4j
public class DateTimeUtil {

    public static final DateTimeFormatter YMD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final String YMDHMS_PATTERN = "yyyy年MM月dd日HH:mm:ss";
    public static final DateTimeFormatter YMDHMS = DateTimeFormatter.ofPattern(YMDHMS_PATTERN);

    public static final String MDHMS_PATTERN = "MM月dd日HH:mm:ss";
    public static final DateTimeFormatter MDHMS = DateTimeFormatter.ofPattern(YMDHMS_PATTERN);

    public static final String MDHM_PATTERN = "MM月dd日HH:mm";
    public static final DateTimeFormatter MDHM = DateTimeFormatter.ofPattern(MDHM_PATTERN);

    public static final String MD_PATTERN = "MM月dd日";
    public static final DateTimeFormatter MD = DateTimeFormatter.ofPattern(MD_PATTERN);

    public static final String HM_PATTERN = "HH:mm";
    public static final DateTimeFormatter HM = DateTimeFormatter.ofPattern(HM_PATTERN);

    public static LocalDateTime convert(Date date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date convert(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String todayOrLastDayFormat(LocalDate today, LocalDateTime localDateTime) {
        String dateTimeFormatStr = MDHM.format(localDateTime);
        String todayFormatStr = MD.format(today);
        String lastDayFormatStr = MD.format(today.minusDays(1));
        if (dateTimeFormatStr.startsWith(todayFormatStr)) {
            return dateTimeFormatStr.replaceFirst(todayFormatStr, "今天");
        }
        if (dateTimeFormatStr.startsWith(lastDayFormatStr)) {
            return dateTimeFormatStr.replaceFirst(lastDayFormatStr, "昨天");
        }
        return dateTimeFormatStr;
    }
}
