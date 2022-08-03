package com.azriasat.reactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoService {

    public Flux<String> fruitFlux(){
       return Flux.fromIterable(List.of("Mango","Orange","Banana")).log();
    }
    public Flux<String> fruitFluxMap(){
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitFluxFilter(int Number){
        return Flux.fromIterable(List.of("Mango","Orange","Banana")).filter(s -> s.length() > Number).log();
    }
    public Flux<String> fruitFluxFilterDoOn(int Number){
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .filter(s -> s.length() > Number).log()
                .doOnNext(s -> System.out.println("s = " + s))
                .doOnSubscribe(subscription -> System.out.println("subscription.toString() = " + subscription.toString()))
                .doOnComplete(()-> System.out.println("Completed!!! "));

    }
    public Flux<String> fruitFluxFilterMap(int Number){
        return Flux.fromIterable(List.of("Mango","Orange","Banana")).filter(s -> s.length() > Number).map(String::toUpperCase).log();
    }

    public Flux<String> fruitFluxTransform(int Number){
        Function<Flux<String>,Flux<String>>  filterData = data -> data.filter(s -> s.length() > Number);

        return Flux.fromIterable(List.of("Mango","Orange","Banana")).transform(filterData).log();
    }

    public Flux<String> fruitFluxTransformDefaultIfEmpty(int Number){
        Function<Flux<String>,Flux<String>>  filterData = data -> data.filter(s -> s.length() > Number);

        return Flux.fromIterable(List.of("Mango","Orange","Banana")).transform(filterData).defaultIfEmpty("Default").log();
    }

    public Flux<String> fruitFluxTransformSwitchIfEmpty(int Number){
        Function<Flux<String>,Flux<String>>  filterData = data -> data.filter(s -> s.length() > Number);

        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .transform(filterData).switchIfEmpty(Flux.just("Pineapple","Jack fruit").transform(filterData)).log();
    }


    public Flux<String> fruitFluxFlatMap(){
        return Flux.fromIterable(List.of("Mango","Orange","Banana")).flatMap(s -> Flux.just(s.split("")));
    }
    public Flux<String> fruitFluxFlatMapAsync(){
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))

                .flatMap(s -> Flux.just(s.split(""))).delayElements( Duration.ofMillis(new Random().nextInt(1000))).log();
    }

    public Flux<String> fruitFluxConcatMap(){
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))

                .concatMap(s -> Flux.just(s.split(""))).delayElements( Duration.ofMillis(new Random().nextInt(1000))).log();
    }

    public Flux<String> fruitMonoFlatMapToMany(){
        return Mono.just("Mango").flatMapMany(s -> Flux.just(s.split("")));
    }

    public Flux<String> fruitFluxConcat(){
        var fruits= Flux.just("Mango","Orange");
        var vegge = Flux.just("Tomato","Lemon");
        return Flux.concat(fruits,vegge);
    }
    public Flux<String> fruitFluxMerge(){
        var fruits= Flux.just("Mango","Orange").delayElements(Duration.ofMillis(50));
        var vegge = Flux.just("Tomato","Lemon").delayElements(Duration.ofMillis(75));
        return Flux.merge(fruits,vegge);
    }
    public Flux<String> fruitFluxMergeWith(){
        var fruits= Flux.just("Mango","Orange").delayElements(Duration.ofMillis(50));
        var vegge = Flux.just("Tomato","Lemon").delayElements(Duration.ofMillis(75));
        return fruits.mergeWith(vegge);
    }
    public Flux<String> fruitFluxMergeWithSequential(){
        var fruits= Flux.just("Mango","Orange").delayElements(Duration.ofMillis(50));
        var vegge = Flux.just("Tomato","Lemon").delayElements(Duration.ofMillis(75));
        return Flux.mergeSequential(fruits,vegge);
    }

    public Flux<String> fruitFluxZip(){
        var fruits= Flux.just("Mango","Orange").delayElements(Duration.ofMillis(50));
        var vegge = Flux.just("Tomato","Lemon").delayElements(Duration.ofMillis(75));
        return Flux.zip(fruits,vegge,(first,second)->first+second);
    }

    public Flux<String> fruitFluxZipWith(){
        var fruits= Flux.just("Mango","Orange").delayElements(Duration.ofMillis(50));
        var vegge = Flux.just("Tomato","Lemon").delayElements(Duration.ofMillis(75));
        return fruits.zipWith(vegge,(first,second)->first+second);
    }
    public Flux<String> fruitFluxZipTuples(){
        var fruits= Flux.just("Mango","Orange").delayElements(Duration.ofMillis(50));
        var vegge = Flux.just("Tomato","Lemon").delayElements(Duration.ofMillis(75));
        var moreVegge = Flux.just("Potato","Beans").delayElements(Duration.ofMillis(75));
        return Flux.zip(fruits,vegge,moreVegge).map(objects -> objects.getT1()+objects.getT2()+objects.getT3());
    }


    public Flux<String> fruitMonoMergeWith(){
        var fruits= Flux.just("Mango").delayElements(Duration.ofMillis(50));
        var vegge = Flux.just("Tomato").delayElements(Duration.ofMillis(75));
        return fruits.mergeWith(vegge);
    }
    public Mono<String> fruitMonoZipWith(){
        var fruits= Mono.just("Mango");
        var vegge = Mono.just("Tomato");
        return fruits.zipWith(vegge,(first,second)->first+second);
    }

    public Flux<String> fruitFluxConcatWith(){
        var fruits= Flux.just("Mango","Orange");
        var vegge = Flux.just("Tomato","Lemon");
        return fruits.concatWith(vegge);
    }

    public Flux<String> fruitMonoConcatWith(){
        var fruits= Flux.just("Mango");
        var vegge = Flux.just("Tomato");
        return fruits.concatWith(vegge);
    }

    public Mono<String> fruitMono(){
        return Mono.just("Mango").log();
    }

    public Mono<List<String>> fruitMonoFlatMapAsync(){
        return Mono.just("Mango")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .delayElement(Duration.ofMillis(new Random().nextInt(1000)))
                .log();

    }

    public Flux<String>  fruitFluxOnError(){
        return Flux.just("Apple","Mango")
                .concatWith(Flux.error( new RuntimeException("Exception Occured"))).onErrorReturn("Orange");
    }
    public Flux<String>  fruitFluxOnErrorContinue() {
        return Flux.just("Apple", "Orange", "Mango")
                .map(s -> {
                    if (s.equalsIgnoreCase("Orange"))
                        throw new RuntimeException("Exception Occured");
                    return s.toUpperCase();
                }).onErrorContinue((e, f) -> {
                    System.out.println("e = " + e);
                    System.out.println("f = " + f);
                });
    }
    public Flux<String>  fruitFluxOnErrorMap() {
        return Flux.just("Apple", "Orange", "Mango")
                .map(s -> {
                    if (s.equalsIgnoreCase("Orange"))
                        throw new RuntimeException("Exception Occured");
                    return s.toUpperCase();
                }).onErrorMap(throwable -> {
                    System.out.println("throwable = " + throwable);
                    return new IllegalStateException("From on ErrorMap");
                });
    }
    public Flux<String>  fruitFluxDoOnError() {
        return Flux.just("Apple", "Orange", "Mango")
                .map(s -> {
                    if (s.equalsIgnoreCase("Orange"))
                        throw new RuntimeException("Exception Occured");
                    return s.toUpperCase();
                }).doOnError(throwable -> {
                    System.out.println("throwable = " + throwable);

                });
    }



    public static void main(String[] args) {
        FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();
        fluxAndMonoService.fruitFlux().subscribe(s -> {
            System.out.println("Flux -> S = " + s);
        });
        fluxAndMonoService.fruitMono().subscribe(s -> {
            System.out.println("Mono -> S = " + s);
        });
    }
}
