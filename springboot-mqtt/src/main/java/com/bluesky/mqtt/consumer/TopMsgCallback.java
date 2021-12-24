package com.bluesky.mqtt.consumer;

import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TopMsgCallback implements MqttCallback {

    private static Logger logger = LoggerFactory.getLogger(TopMsgCallback.class);

    private MqttClient client;
    private MqttConnectOptions options;
    private String[] topic;
    private int[] qos;

    public TopMsgCallback() {
    }

    public TopMsgCallback(MqttClient client, MqttConnectOptions options, String[] topic, int[] qos) {
        this.client = client;
        this.options = options;
        this.topic = topic;
        this.qos = qos;
    }

    /**
     * 断开重连
     */
    @Override
    public void connectionLost(Throwable cause) {
        logger.info("MQTT DISCONNECT， RECONNECTION");
        while (true) {
            try {
                Thread.sleep(30000);
                client.connect(options);
                //订阅消息
                client.subscribe(topic, qos);
                logger.info("MQTT RECONNECTION SUCCESS:" + client);
                break;
            } catch (Exception e) {
                logger.error("connectionLost :" + e);
            }
        }

    }

    /**
     * 接收到消息调用令牌中调用
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
    }

    /**
     * 消息处理
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        //订阅消息字符
        String msg = new String(message.getPayload());
        byte[] bymsg = getBytesFromObject(msg);
        logger.info("topic:" + topic);
        logger.info("msg:" + msg);
    }

    /**
     * 对象转化为字节码
     */
    public byte[] getBytesFromObject(Serializable obj) throws Exception {
        if (obj == null) {
            return null;
        }
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);
        return bo.toByteArray();
    }
}
