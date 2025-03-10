package com.doctor.file_processor.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SchedulerConfig {

    @Scheduled(cron = "0 * * * * * ")
    public void getFuelPrices() throws IOException {
    }
}
