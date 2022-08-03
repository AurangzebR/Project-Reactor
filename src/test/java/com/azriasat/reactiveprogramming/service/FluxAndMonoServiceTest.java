package com.azriasat.reactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoServiceTest {
    FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();

    @Test
    void fruitFlux() {
        var fruitFlux = fluxAndMonoService.fruitFlux();
        StepVerifier.create(fruitFlux).expectNext("Mango","Orange","Banana").verifyComplete();
    }

    @Test
    void fruitMono() {
        var fruitMono = fluxAndMonoService.fruitMono();
        StepVerifier.create(fruitMono).expectNext("Mango").verifyComplete();
    }

    @Test
    void fruitFluxMap(){
        var fruitFlux = fluxAndMonoService.fruitFluxMap();
        StepVerifier.create(fruitFlux).expectNext("MANGO","ORANGE","BANANA").verifyComplete();
    }

    @Test
    void fruitFluxFilterMap(){
        var fruitFlux = fluxAndMonoService.fruitFluxFilterMap(5);
        StepVerifier.create(fruitFlux).expectNext("ORANGE","BANANA").verifyComplete();
    }


    @Test
    void fruitFluxFilter(){
        var fruitFluxFilter = fluxAndMonoService.fruitFluxFilter(5);
        StepVerifier.create(fruitFluxFilter).expectNext("Orange","Banana").verifyComplete();
    }
    @Test
    void fruitFluxFilterDoOn(){
        var fruitFluxFilter = fluxAndMonoService.fruitFluxFilterDoOn(5);
        StepVerifier.create(fruitFluxFilter).expectNext("Orange","Banana").verifyComplete();
    }

    @Test
    void fruitFluxOnError(){
        var fruits = fluxAndMonoService.fruitFluxOnError();
        StepVerifier.create(fruits).expectNext("Apple","Mango","Orange").verifyComplete();
    }

    @Test
    void fruitFluxOnErrorContinue(){
        var fruit = fluxAndMonoService.fruitFluxOnErrorContinue();
        StepVerifier.create(fruit).expectNext("APPLE","MANGO");
    }

    @Test
    void fruitFluxOnErrorMap(){
        var fruit = fluxAndMonoService.fruitFluxOnErrorMap();
        StepVerifier.create(fruit).expectNext("APPLE")
                .expectError(IllegalStateException.class).verify();
    }

    @Test
    void fruitFluxDoOnError()
    {
        var fruit = fluxAndMonoService.fruitFluxDoOnError();
        StepVerifier.create(fruit).expectNext("APPLE").expectError(RuntimeException.class).verify();
    }

    @Test
    void fruitFluxFlatMap(){
        var fruitFlux = fluxAndMonoService.fruitFluxFlatMap();
        StepVerifier.create(fruitFlux).expectNextCount(17).verifyComplete();
    }
    @Test
    void fruitFluxFlatMapAsync(){
        var fruitFlux = fluxAndMonoService.fruitFluxFlatMapAsync();
        StepVerifier.create(fruitFlux).expectNextCount(17).verifyComplete();
    }
    @Test
    void fruitFluxConcatMap(){
        var fruitFlux = fluxAndMonoService.fruitFluxFlatMapAsync();
        StepVerifier.create(fruitFlux).expectNextCount(17).verifyComplete();
    }
    @Test
    void fruitFluxTransform(){
        var fruitFlux = fluxAndMonoService.fruitFluxTransform(5);
        StepVerifier.create(fruitFlux).expectNext("Orange","Banana").verifyComplete();
    }
    @Test
    void fruitFluxTransformDefaultIfEmpty(){
        var fruitFlux = fluxAndMonoService.fruitFluxTransformDefaultIfEmpty(10);
        StepVerifier.create(fruitFlux).expectNext("Default").verifyComplete();
    }

    @Test
    void fruitFluxTransformSwitchIfEmpty(){
        var fruitFlux = fluxAndMonoService.fruitFluxTransformSwitchIfEmpty(8);
        StepVerifier.create(fruitFlux).expectNext("Pineapple","Jack fruit").verifyComplete();
    }
    @Test
    void fruitFluxConcat(){
        var fruitVegge = fluxAndMonoService.fruitFluxConcat();
        StepVerifier.create(fruitVegge).expectNext("Mango","Orange","Tomato","Lemon").verifyComplete();
    }

    @Test
    void fruitFluxConcatWith(){
        var fruitVegge = fluxAndMonoService.fruitFluxConcatWith();
        StepVerifier.create(fruitVegge).expectNext("Mango","Orange","Tomato","Lemon").verifyComplete();
    }


    @Test
    void fruitMonoConcatWith(){
        var fruitVegge = fluxAndMonoService.fruitMonoConcatWith();
        StepVerifier.create(fruitVegge).expectNext("Mango","Tomato").verifyComplete();
    }

    @Test
    void fruitFluxMerge(){
        var fruitVegge = fluxAndMonoService.fruitFluxMerge();
        StepVerifier.create(fruitVegge).expectNext("Mango","Tomato","Orange","Lemon").verifyComplete();
    }
    @Test
    void fruitFluxMergeWith(){
        var fruitVegge = fluxAndMonoService.fruitFluxMergeWith();
        StepVerifier.create(fruitVegge).expectNext("Mango","Tomato","Orange","Lemon").verifyComplete();
    }
    @Test
    void fruitFluxMergeWithSequential(){
        var fruitVegge = fluxAndMonoService.fruitFluxMergeWithSequential();
        StepVerifier.create(fruitVegge).expectNext("Mango","Orange","Tomato","Lemon").verifyComplete();
    }
    @Test
    void fruitFluxZip(){
        var fruitVegge = fluxAndMonoService.fruitFluxZip();
        StepVerifier.create(fruitVegge).expectNext("MangoTomato","OrangeLemon").verifyComplete();
    }

    @Test
    void fruitFluxZipWIth(){
        var fruitVegge = fluxAndMonoService.fruitFluxZipWith();
        StepVerifier.create(fruitVegge).expectNext("MangoTomato","OrangeLemon").verifyComplete();
    }
    @Test
    void fruitFluxZipTuples(){
        var fruitVegge = fluxAndMonoService.fruitFluxZipTuples();
        StepVerifier.create(fruitVegge).expectNext("MangoTomatoPotato","OrangeLemonBeans ").verifyComplete();
    }

    @Test
    void fruitMonoZipWIth(){
        var fruitVegge = fluxAndMonoService.fruitMonoZipWith();
        StepVerifier.create(fruitVegge).expectNext("MangoTomato").verifyComplete();
    }

    @Test
    void fruitMonoMergeWith(){
        var fruitVegge = fluxAndMonoService.fruitMonoMergeWith();
        StepVerifier.create(fruitVegge).expectNext("Mango","Tomato").verifyComplete();
    }


    @Test
    void fruitMonoFlatMapToMany(){
        var fruitFlux= fluxAndMonoService.fruitMonoFlatMapToMany();
        StepVerifier.create(fruitFlux).expectNextCount(5).verifyComplete();
    }

    @Test
    void fruitMonoFlatMapAsync(){
        var fruitFlux = fluxAndMonoService.fruitMonoFlatMapAsync();
        StepVerifier.create(fruitFlux).expectNextCount(1).verifyComplete();
    }
}