package com.bluesky.tech.flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

/**
 * https://skyao.io/learning-reactor/docs/concept/flux/create.html
 */
public class CreateFlux {

    public static void main(String[] args) {
        CreateFlux createFlux = new CreateFlux();
//        createFlux.createMethod();
//        createFlux.generate();
//        createFlux.generate2();
//        createFlux.create();
//        createFlux.bufferTest();
        createFlux.test();
    }



    public void test() {
        Flux.create((t) -> {
            t.next("create");
            t.next("create1");

        }).subscribe(st->{
            System.out.println(st);
        });

    }

    /**
     *静态方法
     * 通过 Flux 类中的静态方法：
     *
     * just()：可以指定序列中包含的全部元素。创建出来的 Flux 序列在发布这些元素之后会自动结束。
     * fromArray()，fromIterable()和 fromStream()：可以从一个数组、Iterable 对象或 Stream 对象中创建 Flux 对象。
     * empty()：创建一个不包含任何元素，只发布结束消息的序列。
     * error(Throwable error)：创建一个只包含错误消息的序列。
     * never()：创建一个不包含任何消息通知的序列。
     * range(int start, int count)：创建包含从 start 起始的 count 个数量的 Integer 对象的序列。
     * interval(Duration period)和 interval(Duration delay, Duration period)：创建一个包含了从 0 开始递增的 Long 对象的序列。其中包含的元素按照指定的间隔来发布。除了间隔时间之外，还可以指定起始元素发布之前的延迟时间。
     * intervalMillis(long period)和 intervalMillis(long delay, long period)：与 interval()方法的作用相同，只不过该方法通过毫秒数来指定时间间隔和延迟时间。
     * 静态方法适合生成简单的序列，当需要复杂的逻辑时，则应该使用 generate() 或 create() 方法。
     */
    private void createMethod(){
        Flux.just("Hello", "World").subscribe(System.out::println);
        Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
        Flux.empty().subscribe(System.out::println);
        Flux.range(1, 10).subscribe(System.out::println);
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
//        Flux.intervalMillis(1000).subscribe(System.out::println);
    }

    /**
     * generate() 方法
     * generate() 方法通过同步和逐一的方式来产生 Flux 序列。
     * 同步是指序列的产生是通过调用所提供的 SynchronousSink 对象的 next()，complete()和 error(Throwable)方法来完成的。
     * 逐一生成的含义是在具体的生成逻辑中，next() 方法只能最多被调用一次。
     */
    private void generate(){
        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);
//        Consumer<SynchronousSink<String>> generator = null;
//        SynchronousSink<String> t1 = null;
//        generator.accept(t1);
    }

    /**
     *在有些情况下，序列的生成可能是有状态的，需要用到某些状态对象。
     * 此时可以使用 generate() 方法的另外一种形式 generate(Callable<S> stateSupplier, BiFunction<S,SynchronousSink<T>,S> generator)，
     * 其中 stateSupplier 用来提供初始的状态对象。
     * 在进行序列生成时，状态对象会作为 generator 使用的第一个参数传入，可以在对应的逻辑中对该状态对象进行修改以供下一次生成时使用。
     */
    private void generate2(){
        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
    }

    /**
     * create()方法与 generate()方法的不同之处在于所使用的是 FluxSink 对象。
     * FluxSink 支持同步和异步的消息产生，并且可以在一次调用中产生多个元素。下面的代码在一次调用中就产生了全部的 10 个元素：
     */
    private void create(){
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
        Flux<Object> objectFlux = Flux.create(sink -> {
            for (int i = 22; i < 30; i++) {
                sink.next(i);
            }
            sink.complete();
        });
        objectFlux.subscribe(x -> System.out.println("x:" + x));
    }

    private void bufferTest(){
        // 输出的是 5 个包含 20 个元素的数组
        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
        // 输出的是 2 个包含了 10 个元素的数组
        //Flux.intervalMillis(100).bufferMillis(1001).take(2).toStream().forEach(System.out::println);
        //bufferUntil 会一直收集直到 Predicate 返回为 true。使得 Predicate 返回 true 的那个元素可以选择添加到当前集合或下一个集合中；
        // 输出的是 5 个包含 2 个元素的数组   每当遇到一个偶数就会结束当前的收集
        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        //bufferWhile 则只有当 Predicate 返回 true 时才会收集。一旦值为 false，会立即开始下一次收集。
        // 第四行语句输出的是 5 个包含 1 个元素的数组  数组里面包含的只有偶数。
        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
        //filter操作符对流中包含的元素进行过滤，只留下满足 Predicate 指定条件的元素。
        //下面代码的输出的是 1 到 10 中的所有偶数。
        System.out.println("----filter-------");
        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);
        //windows
        System.out.println("----window----");
        Flux.range(1, 100).window(20).subscribe(System.out::println);
//        Flux.intervalMillis(100).windowMillis(1001).take(2).toStream().forEach(System.out::println);



    }



}
