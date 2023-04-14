package com.bluesky.tech.flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * https://zhuanlan.zhihu.com/p/53510418
 */
public class FluxDemo {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("the","quick","brown","fox");
        Flux.fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .concatWith(Mono.just("s")).distinct().sort()
                .zipWith(Flux.range(1,Integer.MAX_VALUE),
                        (string,count)->String.format("%2d.%s",count,string)
                )
                .subscribe(System.out::println);
    }
}
