package com.gousade.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SchedulerTaskTests {
    @Autowired
    private SchedulerTask schedulerTask;

    public void testMiHoYoAutoSignInSpecifiedGroup() {
        schedulerTask.miHoYoAutoSignInSpecifiedGroup();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
