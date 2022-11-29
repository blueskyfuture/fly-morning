package com.bluesky.tech.reactive;

import reactor.core.publisher.Mono;

import java.util.Optional;

public class MonoDemo {

    private boolean isAuthenticated;

    //传统数据处理
    public ClientUser currentUser1 () {
        return isAuthenticated ? new ClientUser("felord.cn", "reactive") : null;
    }

    //Optional的处理方式
    public Optional<ClientUser> currentUser2 () {
        return isAuthenticated ? Optional.of(new ClientUser("felord.cn", "reactive"))
                : Optional.empty();
    }

    //反应式数据处理
    public Mono<ClientUser> currentUser3 () {
        return isAuthenticated ? Mono.just(new ClientUser("felord.cn", "reactive"))
                : Mono.empty();
    }
}
