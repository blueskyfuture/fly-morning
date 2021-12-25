package com.bluesky.kafka.avro.rpc;

import com.bluesky.kafka.avro.Mail;
import com.bluesky.kafka.avro.Message;
import org.apache.avro.ipc.NettyTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;
import org.apache.avro.util.Utf8;

import java.net.InetSocketAddress;

public class AvroClient {
    public static void main(String[] args) throws Exception {
        NettyTransceiver client = new NettyTransceiver(new InetSocketAddress(65111));
        /// 获取Mail接口的proxy实现
        Mail proxy = SpecificRequestor.getClient(Mail.class, client);
        System.out.println("Client of Mail Proxy is built");

        // fill in the Message record and send it
        args = new String[] { "to:Tom", "from:Jack", "body:How are you" };
        Message message = new Message();
        message.setTo(new Utf8(args[0]));
        message.setFrom(new Utf8(args[1]));
        message.setBody(new Utf8(args[2]));
        System.out.println("RPC call with message:  " + message.toString());

        /// 底层给服务器发送send方法调用
        System.out.println("Result: " + proxy.send(message));

        // cleanup
        client.close();
    }
}
