package com.azriasat.reactiveprogramming.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotAndColdStreamTest {

    @Test
    public void coldStreamTest(){
        var number = Flux.range(1,10);
        number.subscribe(integer -> {
            System.out.println("Subscriber 1 = " + integer);
        });
        number.subscribe(integer -> {
            System.out.println("Subscriber 2 = " + integer);
        });

    }

    @Test
    @SneakyThrows
    public void hotStreamTest(){
        var number = Flux.range(1,10).delayElements(Duration.ofMillis(1000));
        ConnectableFlux<Integer> publisher = number.publish();
        publisher.connect();
        publisher.subscribe(integer -> {
            System.out.println("Subscriber 1 = " + integer);
        });
        Thread.sleep(4000);
        publisher.subscribe(integer -> {
            System.out.println("Subscriber 2 = " + integer);
        });
        Thread.sleep(10000);
    }
}
