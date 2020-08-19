package com.ffw.admin.task;

import com.ffw.admin.service.pan.Sync;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author fufengwen
 * @Time 2020/7/24 11:54
 */
@Component
public class ScheduledTask {

    @Autowired
    private Sync sync;

    @Scheduled(fixedRate = 1 * 60 * 1000)
    public void scheduledTask() {
        System.out.println("--------------------------");
        System.out.println("--------------------------");
        System.out.println("任务执行时间(每1分钟执行一次)：" + LocalDateTime.now());
        sync.syncGooglePan();
        System.out.println("--------------------------");
        System.out.println("--------------------------");
    }

}

