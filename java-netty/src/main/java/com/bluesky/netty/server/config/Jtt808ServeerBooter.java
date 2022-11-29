package com.bluesky.netty.server.config;

import com.bluesky.netty.server.server.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Jtt808ServeerBooter implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Jtt808ServeerBooter begin...");
        NettyServer nettyServer = new NettyServer(20082);
        try {
            nettyServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
