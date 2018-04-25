package com.tjlcast.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by tangjialiang on 2018/4/25.
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    @Autowired
    private KafkaSender kafkaSender;

    public static void main(String[] args) {
        SpringApplication.run(Application .class, args);
    }


    //然后每隔1分钟执行一次
    @Scheduled(fixedRate = 1000 * 60)
    public void testKafka() throws Exception {
        kafkaSender.sendTest();
    }
}