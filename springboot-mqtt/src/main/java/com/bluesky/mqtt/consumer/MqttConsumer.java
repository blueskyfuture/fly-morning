package com.bluesky.mqtt.consumer;

import com.bluesky.mqtt.config.MqttProperties;
import org.springframework.boot.ApplicationRunner;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/**
 * mqtt消费端注入
 */
@Component
public class MqttConsumer implements ApplicationRunner {

    private static Logger logger = LoggerFactory.getLogger(MqttConsumer.class);

    private static MqttClient client;

    private static MqttTopic mqttTopic;

    /**
     * MQTT连接属性配置对象
     */
    @Autowired
    public MqttProperties mqttCofigBean;

    /**
     * 初始化参数配置
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("INIT MQTT CONNECT");
        this.connect();
    }


    /**
     * 用来连接服务器
     */
    private void connect() throws Exception {
        client = new MqttClient(mqttCofigBean.getUrl(), mqttCofigBean.getConsumerClientId(), new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(mqttCofigBean.getUsername());
        options.setPassword(mqttCofigBean.getPassword().toCharArray());
        //是否清除session
        options.setCleanSession(false);
        // 设置超时时间
        options.setConnectionTimeout(30);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            //String[] msgtopic = mqttCofigBean.getMsgTopic();
            String[] msgtopic = {"topic01", "topic02"};
            //订阅消息
            int[] qos = new int[msgtopic.length];
            for (int i = 0; i < msgtopic.length; i++) {
                qos[i] = 0;
            }
            client.setCallback(new TopMsgCallback(client, options, msgtopic, qos));
            client.connect(options);
            client.subscribe(msgtopic, qos);
            logger.info("MQTT CONNECT SUCCESS:{}:{}", mqttCofigBean.getClientId(), client);
        } catch (Exception e) {
            logger.error("MQTT CONNECT EXCEPTION：" + e);
        }
    }


    /**
     * 重连
     *
     * @throws Exception E
     */
    public void reConnect() throws Exception {
        if (null != client) {
            this.connect();
        }
    }

    /**
     * 订阅某个主题
     */
    public void subscribe(String topic, int qos) {
        try {
            logger.info("TOPIC:" + topic);
            client.subscribe(topic, qos);
        } catch (MqttException e) {
            logger.error(e.getMessage());
        }
    }

    public MqttClient getClient() {
        return client;
    }

    public void setClient(MqttClient client) {
        MqttConsumer.client = client;
    }

    public MqttTopic getMqttTopic() {
        return mqttTopic;
    }

    public void setMqttTopic(MqttTopic mqttTopic) {
        MqttConsumer.mqttTopic = mqttTopic;
    }
}