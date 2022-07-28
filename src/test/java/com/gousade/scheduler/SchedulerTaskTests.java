package com.gousade.scheduler;

import com.gousade.scheduler.job.MiHoYoJob;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SchedulerTaskTests {
    @Autowired
    private SchedulerTask schedulerTask;

    @Autowired
    private MiHoYoJob miHoYoJob;

    @Test
    public void testMiHoYoAutoSignInSpecifiedGroup() {
        miHoYoJob.miHoYoAutoSignInSpecifiedGroup();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
