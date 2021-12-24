package com.bluesky.mqtt_.config1;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

//@Component
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttTemplate {
    void send(String payload);

    void sendToTopic(@Header(MqttHeaders.TOPIC) String topic, String payload);

    void sendToTopic(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);

}
