package com.nutrition.nutritionservice.schedule;

import com.google.common.collect.Lists;
import com.nutrition.nutritionservice.service.ElemeManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * 饿了么定时任务
 *
 * @author heng.liu
 * @since 2021/6/16
 */
@Component
@Slf4j
public class ElemeScheduleTask {

    @Resource
    private ElemeManageService elemeManageService;

    private List<Long> shopIdList = Lists.newArrayList();

    @Autowired
    public ElemeScheduleTask() {
        this.shopIdList.add(2096299733L);
//        this.shopIdList.add(2086662834L);
    }

    @Scheduled(cron = "0 * * * * ? ")
    public void autoPrepared() {
        log.info("Start preparing ELEME orders!!!");
        for (Long aLong : shopIdList) {
            elemeManageService.prepareAll(aLong, LocalDate.now());
        }
        log.info("Finish preparing ELEME orders!!!");
    }

}
