package com.bluesky.mqtt.service;

import com.bluesky.mqtt.config3.MqttGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MqttSendService {

    @Autowired
    private MqttGateway mqttSender3;

    @Autowired
    @Qualifier(value = "taskExecutor")
    private ThreadPoolTaskExecutor poolTaskExecutor;

    public String sendMsg(String topic, String msg){
        System.out.println("sendMsg--begin...");
        for (int i = 0; i < 20; i++) {
            final int tmp = i;
            poolTaskExecutor.execute(()->{
                mqttSender3.sendToMqtt(topic,  msg + "-threadpool-" + tmp);
            });
        }
        System.out.println("sendMsg--end...");
        return "finish";
    }

    @Async("taskExecutor")
    public String sendMsgAsync(String topic, String msg){
        System.out.println("sendMsgAsync--begin...");
        for (int i = 0; i < 20; i++) {
            final int tmp = i;
            poolTaskExecutor.execute(()->{
                mqttSender3.sendToMqtt(topic,  msg + "-threadpool-" + tmp);
            });
        }
        System.out.println("sendMsgAsync--end...");
        return "finish";
    }
}
