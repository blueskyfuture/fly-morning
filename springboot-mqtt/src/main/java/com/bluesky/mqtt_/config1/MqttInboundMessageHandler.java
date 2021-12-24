package com.bluesky.mqtt_.config1;


import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * 接收消息
 */
@Slf4j
@Component
public class MqttInboundMessageHandler implements MessageHandler {
    public static final String RECEIVED_TOPIC_KEY = "mqtt_receivedTopic";

    /**
     * 根据MqttConfigSub中mqttInbound设置topic-reply接收消息
     * @param message
     * @throws MessagingException
     */
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String topic = message.getHeaders().get(RECEIVED_TOPIC_KEY).toString();
        log.info("{} mqtt reply: {}", topic, message.getPayload());
    }
}
