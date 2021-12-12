package com.bluesky.mqtt.config;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

//@Component
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttTemplate {
    void send(String payload);

    void sendToTopic(@Header(MqttHeaders.TOPIC) String topic, String payload);

    void sendToTopic(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);

}
