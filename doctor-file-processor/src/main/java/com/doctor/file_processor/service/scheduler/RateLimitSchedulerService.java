package com.doctor.file_processor.service.scheduler;

import com.doctor.file_processor.service.AccessInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RateLimitSchedulerService {
    private final AccessInfoService accessInfoService;

    public RateLimitSchedulerService(AccessInfoService accessInfoService) {
        this.accessInfoService = accessInfoService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void resetRateLimit() {
        // Ideally each client should also be bound to fixed remote IP or have specific number of changes for 24 hours.
        accessInfoService.deleteAll();
    }

}
