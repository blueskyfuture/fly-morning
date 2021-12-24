package com.bluesky.mqtt.controller;

import com.bluesky.mqtt.config3.MqttGateway;
import com.bluesky.mqtt.service.MqttSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对应config3
 */
@RestController
public class TestController3 {

    @Autowired
    private MqttGateway mqttSender3;
    @Autowired
    private MqttSendService mqttSendService;
    
    /**
     * 发送自定义消息内容，且指定主题
     * http://127.0.0.1:8080/test2/obu/bsm124
      */
    @RequestMapping("/test2/{topic}/{data}")
    public String test2(@PathVariable("topic") String topic, @PathVariable("data") String data) {
        //多线程发送
        mqttSender3.sendToMqtt(topic, data);
        return ""+System.currentTimeMillis();
    }

    /**
     * 发送自定义消息内容，且指定主题
     * http://127.0.0.1:8080/test3?topic=/v1/obu/datang&data=datang.bsm125
      */
    @RequestMapping("/test3")
    public String test3(@RequestParam("topic") String topic, @RequestParam("data") String data) {
        mqttSender3.sendToMqtt(topic, data);
        return ""+System.currentTimeMillis();
    }

    /**
     * 通过线程池发送
     * http://127.0.0.1:8080/test4?topic=topic01&data=datang.bsm125
     */
    @RequestMapping("/test4")
    public String test4(@RequestParam("topic") String topic, @RequestParam("data") String data) {
        mqttSendService.sendMsg(topic,data);
        return ""+System.currentTimeMillis();
    }

    /**
     * 通过线程池发送
     * http://127.0.0.1:8080/test5?topic=topic01&data=datang.bsm125
     */
    @RequestMapping("/test5")
    public String test5(@RequestParam("topic") String topic, @RequestParam("data") String data) {
        mqttSendService.sendMsgAsync(topic,data);
        return ""+System.currentTimeMillis();
    }
}
