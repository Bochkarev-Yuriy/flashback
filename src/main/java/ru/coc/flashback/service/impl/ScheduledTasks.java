package ru.coc.flashback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yuriy Bochkarev
 * @since 18.12.2018.
 */

@Component
public class ScheduledTasks {

    @Autowired
    private LogickService logickService;

    @Scheduled(cron = "${scheduled.api.clashofclans.com.currentwar.leaguegroup}")
    private void reportCurrentTime() {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " Scheduled run synchronize source clans current war league group");
        logickService.execute();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " Scheduled end synchronize source clans current war league group");
    }
}
