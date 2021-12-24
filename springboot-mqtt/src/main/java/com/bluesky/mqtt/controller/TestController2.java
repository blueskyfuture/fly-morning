package com.bluesky.mqtt.controller;

import com.bluesky.mqtt_.config2.MqttGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 对应config2
 */
//@RestController
public class TestController2 {

    @Autowired
    private MqttGateway mqttSender;

    /**
     * 发送自定义消息内容，且指定主题
     * http://127.0.0.1:8080/test2/obu/bsm124
      */
    @RequestMapping("/test2/{topic}/{data}")
    public String test2(@PathVariable("topic") String topic, @PathVariable("data") String data) {
        mqttSender.sendToMqtt(topic, data);
        return ""+System.currentTimeMillis();
    }

    /**
     * 发送自定义消息内容，且指定主题
     * http://127.0.0.1:8080/test3?topic=/v1/obu/datang&data=datang.bsm125
      */
    @RequestMapping("/test3")
    public String test3(@RequestParam("topic") String topic, @RequestParam("data") String data) {
        mqttSender.sendToMqtt(topic, data);
        return ""+System.currentTimeMillis();
    }
}
