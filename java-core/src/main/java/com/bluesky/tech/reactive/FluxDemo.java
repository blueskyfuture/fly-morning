package com.bluesky.tech.reactive;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * https://segmentfault.com/a/1190000024499748
 */
public class FluxDemo {

    //传统数据处理
    public List<ClientUser> allUsers1() {
        return Arrays.asList(new ClientUser("felord.cn", "reactive"),
                new ClientUser("Felordcn", "Reactor"));
    }

    //流式数据处理
    public Stream<ClientUser> allUsers2() {
        return  Stream.of(new ClientUser("felord.cn", "reactive"),
                new ClientUser("Felordcn", "Reactor"));
    }

    //反应式数据处理
    public Flux<ClientUser> allUsers3(){
        return Flux.just(new ClientUser("felord.cn", "reactive"),
                new ClientUser("Felordcn", "Reactor"));
    }
}
