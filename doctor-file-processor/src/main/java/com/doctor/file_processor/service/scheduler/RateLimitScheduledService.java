package com.doctor.file_processor.service.scheduler;

import com.doctor.file_processor.service.AccessInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RateLimitScheduledService {
    private final AccessInfoService accessInfoService;

    public RateLimitScheduledService(AccessInfoService accessInfoService) {
        this.accessInfoService = accessInfoService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void resetRateLimit() {
        // Ideally each client should have their own access token assigned and
        // be bound to fixed remote IP or have specific number of ip changes for 24 hours.
        accessInfoService.deleteAll();
    }

}
