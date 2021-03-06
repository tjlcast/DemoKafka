package com.tjlcast.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by tangjialiang on 2018/4/25.
 */
@Component
public class KafkaSender {

    @Resource
    KafkaConsumerPool consumerPool;

    /**
     *  这里需要放到程序启动完成之后执行 TODO
     */
    @PostConstruct
    void d(){

        ConsumerGroup consumerThread = new ConsumerGroup("gropu-1","access_data",consumerConfig);
        ConsumerGroup consumerThread2 = new ConsumerGroup("gropu-2","access_data", consumerConfig);

        /**
         * 各起两个消费者 ,Kafka consumer是非线程安全的 Consumer 需要一个new 的
         */
        consumerPool.SubmitConsumerPool(new Consumer(consumerThread));
        consumerPool.SubmitConsumerPool(new Consumer(consumerThread));

        consumerPool.SubmitConsumerPool(new Consumer(consumerThread2));
        consumerPool.SubmitConsumerPool(new Consumer(consumerThread2));
    }


    @Resource
    KafkaConsumerConfig consumerConfig;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaTopics kafkaTopics;

    /**
     * 发送消息到kafka
     *
     */
    public void sendTest() throws InterruptedException, IOException, KeeperException {

        /**
         * topic='access_data'
         */
        kafkaTemplate.send("access_data",""+ System.currentTimeMillis());
        kafkaTemplate.send("access_data",""+System.currentTimeMillis());
        kafkaTemplate.send("access_data",""+System.currentTimeMillis());
        kafkaTemplate.send("access_data",""+System.currentTimeMillis());
        kafkaTemplate.send("access_data",""+System.currentTimeMillis());
        kafkaTemplate.send("access_data",""+System.currentTimeMillis());
    }


}