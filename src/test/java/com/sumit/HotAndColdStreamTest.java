package com.sumit;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotAndColdStreamTest {


    @Test
    public void coldStreamTest(){
        var numbers= Flux.range(1,10);

        numbers.subscribe(integer -> System.out.println("Subscribers 1 -> "+integer));
        numbers.subscribe(integer -> System.out.println("Subscribers 2 -> "+integer));
    }

    @SneakyThrows
    @Test
    public void hotStreamTest(){
        var numbers= Flux.range(1,10)
                .delayElements(Duration.ofMillis(1000));

        ConnectableFlux<Integer> publisher =numbers.publish();

        publisher.connect();

        publisher.subscribe(integer -> System.out.println("Subscribers 2 -> "+integer));
        Thread.sleep(4000);
        publisher.subscribe(integer -> System.out.println("Subscribers 2 -> "+integer));
        Thread.sleep(10000);
    }
}
