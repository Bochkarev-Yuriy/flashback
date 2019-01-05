package ru.coc.flashback.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.coc.flashback.service.job.RegistrationAccountJobService;
import ru.coc.flashback.service.job.UpdateAccountJobService;

/**
 * @author Yuriy Bochkarev
 * @since 18.12.2018.
 */

@Component
public class ScheduledTasks {

    private static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private final LogickService logickService;
    private final RegistrationAccountJobService registrationAccountJobService;
    private final UpdateAccountJobService updateAccountJobService;

    @Autowired
    public ScheduledTasks(LogickService logickService, RegistrationAccountJobService registrationAccountJobService, UpdateAccountJobService updateAccountJobService) {
        this.logickService = logickService;
        this.registrationAccountJobService = registrationAccountJobService;
        this.updateAccountJobService = updateAccountJobService;
    }

    @Scheduled(cron = "${scheduled.api.clashofclans.com.currentwar.leaguegroup}")
    private void reportCurrentTime() {
        logger.info("Scheduled start: Synchronize source clans current war league group.");
        logickService.execute();
        logger.info("Scheduled finish: Synchronize source clans current war league group.");
    }

    @Scheduled(cron = "${scheduled.registration.account}")
    private void registrationAccount() {
        logger.info("Scheduled start: Registration account.");
        registrationAccountJobService.execute();
        logger.info("Scheduled finish: Registration account.");
    }

    @Scheduled(cron = "${scheduled.update.account}")
    private void updateAccount() {
        logger.info("Scheduled start: Update account.");
        updateAccountJobService.execute();
        logger.info("Scheduled finish: Update account.");
    }
}
