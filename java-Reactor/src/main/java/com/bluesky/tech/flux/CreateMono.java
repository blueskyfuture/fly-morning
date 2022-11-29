package com.bluesky.tech.flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * https://skyao.io/learning-reactor/docs/concept/flux/create.html
 */
public class CreateMono {

    public static void main(String[] args) {
        CreateMono createMono = new CreateMono();
        createMono.createMethod();
    }

    /**
     *
     */
    private void createMethod(){
        Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
        Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);
    }


}
