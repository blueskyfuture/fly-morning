package com.bluesky.mqtt_.config1;

import com.bluesky.mqtt.config.MqttProperties;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.time.Instant;


/**
 * 发布者配置
 */
@Configuration
//此处打开注解@IntegrationComponentScan提示The bean 'MqttTemplate' could not be registered.A bean with that name has already been defined and overriding is disabled.
//@IntegrationComponentScan
public class MqttConfigPub {
    @Autowired
    private MqttProperties prop;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setServerURIs(new String[]{prop.getUrl()});
        mqttConnectOptions.setUserName(prop.getUsername());
        mqttConnectOptions.setPassword(prop.getPassword().toCharArray());
        // 客户端断线时暂时不清除，直到超时注销
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setAutomaticReconnect(true);
        factory.setConnectionOptions(mqttConnectOptions);
        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound(MqttPahoClientFactory mqttClientFactory) {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
                prop.getClientId() + "-pub-" + Instant.now().toEpochMilli(), mqttClientFactory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultRetained(false);
        messageHandler.setAsyncEvents(false);
        // Exactly Once
        messageHandler.setDefaultQos(2);
        //messageHandler.setDefaultTopic(ApiConst.MQTT_TOPIC_SUFFIX);
        messageHandler.setDefaultTopic("topic-test");
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }
}