package com.sumit.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoService {
    public Flux<String> fruitsFlux(){
       return Flux.fromIterable(List.of("Mango","orange","banana")).log();
    }

    public Mono<String> fruitsMono(){
        return Mono.just("mango").log();
    }


    public Flux<String> fruitsFluxMap(){
        return Flux.fromIterable(List.of("Mango","orange","banana"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFilter(int number){
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .filter(s->s.length()>number)
                .log();
    }

    public Flux<String> fruitsFluxFilterMap(int number){
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .filter(s->s.length()>number)
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFlatMap(){
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .flatMap(s->Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsFluxFlatMapAsync(){
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .flatMap(s->Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(100)
                ))
                .log();
    }

    public Flux<String> fruitsFluxConcatMap(){
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .flatMap(s->Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(100)
                ))
                .log();
    }



    public Mono<List<String>> fruitsMonoFlatMap(){
        return Mono.just("mango")
                .flatMap(s->Mono.just(List.of(s.split(""))))
                .log();
    }


    //flatmapmany-> mono to flux
    public Flux<String> fruitsMonoFlatMapMany(){
        return Mono.just("mango")
                .flatMapMany(s->Flux.just(s.split("")))
                .log();
    }


    //transform -> convert from one type to another type

    public Flux<String> fruitsFluxTransform(int number){

        Function<Flux<String>,Flux<String>> filterData = data-> data.filter(s->s.length()>number);


        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .transform(filterData)
                .log();
    }

    public Flux<String> fruitsFluxTransformDefaultIfEmpty(int number){

        Function<Flux<String>,Flux<String>> filterData = data-> data.filter(s->s.length()>number);


        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .transform(filterData)
                .defaultIfEmpty("default")
                .log();
    }

    //to switch to different data set
    public Flux<String> fruitsFluxTransformSwitchIfEmpty(int number){

        Function<Flux<String>,Flux<String>> filterData = data-> data.filter(s->s.length()>number);


        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .transform(filterData)
                .switchIfEmpty(Flux.just("pineapple","Jack fruit"))
                .transform(filterData)
                .log();
    }


    public Flux<String> fruitsFluxConcat(){
        var fruits = Flux.just("mongo","orange");
        var veggis = Flux.just("tomato","lemon");
        return Flux.concat(fruits,veggis).log();
    }


    public Flux<String> fruitsFluxConcatWith(){
        var fruits = Flux.just("mongo","orange");
        var veggis = Flux.just("tomato","lemon");
        return fruits.concatWith(veggis).log();
    }

    public Flux<String> fruitsMonoConcatWith(){
        var fruits = Flux.just("mongo","orange");
        var veggis = Flux.just("tomato","lemon");
        return fruits.concatWith(veggis).log();
    }


    //merge
    public Flux<String> fruitsFluxMerge(){
        var fruits = Flux.just("mongo","orange")
                .delayElements(Duration.ofMillis(50));
        var veggis = Flux.just("tomato","lemon")
                .delayElements(Duration.ofMillis(50));
        return Flux.merge(fruits,veggis).log();
    }

    public Flux<String> fruitsFluxMergeWith(){
        var fruits = Flux.just("mongo", "orange")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("tomato", "lemon")
                .delayElements(Duration.ofMillis(50));

        return fruits.mergeWith(veggies).log();

    }

    public Flux<String> fruitsFluxMergeWithSequential(){
        var fruits = Flux.just("mongo", "orange")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("tomato", "lemon")
                .delayElements(Duration.ofMillis(50));

        return Flux.mergeSequential(fruits,veggies).log();

    }




    public static void main(String[] args) {
        FluxAndMonoService fluxAndMonoService=new FluxAndMonoService();
        fluxAndMonoService.fruitsFlux()
                .subscribe(s->{
                    System.out.println("s-> "+s);
                });

        System.out.println("---------------------------------");

        fluxAndMonoService.fruitsMono()
                .subscribe(s->{
                    System.out.println("s->"+s);
                });


    }
}
