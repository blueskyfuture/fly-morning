package com.bluesky.mqtt.config3;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.Lifecycle;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

public class MultiMqttMessageHandler extends AbstractMessageHandler implements Lifecycle{

    private final AtomicBoolean running = new AtomicBoolean();
    private volatile Map<Integer, MessageHandler> mqttHandlerMap;
    @Value("${spring.mqtt.sender.count}")
    private Integer handlerCount = 5;  //客户端数量
    @Autowired
    private MqttSenderConfig senderConfig;

    @Override
    public void start() {
        System.out.println("start()");
        if (!this.running.getAndSet(true)) {
            doStart();
        }
    }

    private void doStart(){
        System.out.println("doStart()");
        mqttHandlerMap = new ConcurrentHashMap<>();
        for(int i = 0;i < handlerCount;i++){
            System.out.println("doStart()--i:" + i);
            mqttHandlerMap.put(i, senderConfig.createMqttOutbound());
        }
    }

    @Override
    public void stop() {
        if (this.running.getAndSet(false)) {
            doStop();
        }
    }

    private void doStop(){
        for(Map.Entry<Integer, MessageHandler> e : mqttHandlerMap.entrySet()){
            MessageHandler handler = e.getValue();
            ((MyMqttPahoMessageHandler)handler).doStop();
        }
    }

    @Override
    public boolean isRunning() {
        return this.running.get();
    }

    @Override
    protected void handleMessageInternal(Message<?> message){
        Random random = new Random();
        MyMqttPahoMessageHandler messageHandler = (MyMqttPahoMessageHandler)mqttHandlerMap.get(random.nextInt(handlerCount));
        System.out.println("messageHandler.getClientId():" + messageHandler.getClientId());
        messageHandler.handleMessageInternal(message);
    }

}
