package com.bluesky.mqtt.config3;

import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.Message;

public class MyMqttPahoMessageHandler extends MqttPahoMessageHandler{

    public MyMqttPahoMessageHandler(String clientId, MqttPahoClientFactory clientFactory) {
        super(clientId, clientFactory);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void doStop() {
        super.doStop();
    }

    @Override
    public void handleMessageInternal(Message<?> message) {
        super.handleMessageInternal(message);
    }

    @Override
    public void onInit() {
        try {
            super.onInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
