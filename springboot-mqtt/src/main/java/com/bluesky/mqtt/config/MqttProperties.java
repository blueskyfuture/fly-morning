package com.bluesky.mqtt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.mqtt")
public class MqttProperties {
    private String url;
    private String username;
    private String password;
    private String clientId;
    private String consumerClientId;
}