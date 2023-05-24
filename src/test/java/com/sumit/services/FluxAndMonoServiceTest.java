package com.sumit.services;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Hooks;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoServiceTest {

    FluxAndMonoService fluxAndMonoService=new FluxAndMonoService();

    @Test
    void fruitsFlux() {
        var fruitsFlux=fluxAndMonoService.fruitsFlux();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango","ordange","banana")
                .verifyComplete();
    }

    @Test
    void fruitsMono() {
        var fruitsFlux=fluxAndMonoService.fruitsMono();
        StepVerifier.create(fruitsFlux)
                .expectNext("mango")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMap() {
        var fruitsFlux=fluxAndMonoService.fruitsFluxMap();
        StepVerifier.create(fruitsFlux)
                .expectNext("MANGO","ORANGE","BANANA")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilter() {
        var fruitsFlux=fluxAndMonoService.fruitsFluxConcatMap();
        StepVerifier.create(fruitsFlux)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterMap() {
        var fruitsFlux=fluxAndMonoService.fruitsFluxFilterMap(5);
        StepVerifier.create(fruitsFlux)
                .expectNext("ORANGE","BANANA")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMap() {
        var fruitsFlux=fluxAndMonoService.fruitsFluxFlatMap();
        StepVerifier.create(fruitsFlux)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMapAsync() {
        var fruitsFlux = fluxAndMonoService.fruitsFluxFlatMapAsync();

        StepVerifier.create(fruitsFlux)
                .expectNextCount(17)
                .verifyComplete();

    }

    @Test
    void fruitsMonoFlatMap() {
        var fruitsFlux = fluxAndMonoService.fruitsMonoFlatMap();

        StepVerifier.create(fruitsFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatMap() {
        var fruitsFlux = fluxAndMonoService.fruitsMonoFlatMap();

        StepVerifier.create(fruitsFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitsMonoFlatMapMany() {
        var fruitsFlux = fluxAndMonoService.fruitsMonoFlatMapMany();

        StepVerifier.create(fruitsFlux)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransform() {
        var fruitsFlux = fluxAndMonoService.fruitsFluxTransform(5);

        StepVerifier.create(fruitsFlux)
                .expectNext("Orange","Banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformDefaultIfEmpty() {
        var fruitsFlux = fluxAndMonoService.fruitsFluxTransformDefaultIfEmpty(10);

        StepVerifier.create(fruitsFlux)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformSwitchIfEmpty() {
        var fruitsFlux = fluxAndMonoService.fruitsFluxTransformSwitchIfEmpty(8);

        StepVerifier.create(fruitsFlux)
                .expectNext("pineapple","Jack fruit")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcat() {

        var fruitsFlux=fluxAndMonoService.fruitsFluxConcat();
        StepVerifier.create(fruitsFlux)
                .expectNext("mongo","orange","tomato","lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatWith() {
        var fruitsFlux=fluxAndMonoService.fruitsFluxConcatWith();
        StepVerifier.create(fruitsFlux)
                .expectNext("mongo","orange","tomato","lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMerge() {
        var fruitsFlux=fluxAndMonoService.fruitsFluxMerge();
        StepVerifier.create(fruitsFlux)
                .expectNext("mongo","tomato","orange","lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWith() {
        var fruitsFlux=fluxAndMonoService.fruitsFluxMergeWith();
        StepVerifier.create(fruitsFlux)
                .expectNext("mongo", "tomato", "orange", "lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWithSequential() {
        var fruitsFlux=fluxAndMonoService.fruitsFluxMergeWithSequential();
        StepVerifier.create(fruitsFlux)
                .expectNext("mongo", "orange", "tomato", "lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZip() {
        var fruitsFlux=fluxAndMonoService.fruitsFluxZip();
        StepVerifier.create(fruitsFlux)
                .expectNext("mongotomato","orangelemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipWith() {
        var fruitsFlux=fluxAndMonoService.fruitsFluxZipWith();
        StepVerifier.create(fruitsFlux)
                .expectNext("mongotomato","orangelemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipTuple() {
        var fruitsFlux=fluxAndMonoService.fruitsFluxZipTuple();
        StepVerifier.create(fruitsFlux)
                .expectNext("mongotomatopotato","orangelemonbeans")
                .verifyComplete();
    }


    @Test
    void fruitsMonoZipWith() {
        var fruitsFlux=fluxAndMonoService.fruitsMonoZipWith();
        StepVerifier.create(fruitsFlux)
                .expectNext("mongotomato")
                .verifyComplete();
    }

    @Test
    void furuitsFluxFilterDoOn() {
        var fruitsFlux=fluxAndMonoService.furuitsFluxFilterDoOn(5).log();
        StepVerifier.create(fruitsFlux)
                .expectNext("Orange","Banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorReturn() {
        var fruitsFlux=fluxAndMonoService.fruitsFluxOnErrorReturn()
                .log();

        StepVerifier.create(fruitsFlux)
                .expectNext("apple","mongo","Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorContinue() {
        var fruitsFlux=fluxAndMonoService.fruitsFluxOnErrorContinue()
                .log();

        StepVerifier.create(fruitsFlux)
                .expectNext("APPLE","ORANGE")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorMap() {
//        Hooks.onOperatorDebug();
        var fruitsFlux=fluxAndMonoService.fruitsFluxOnErrorMap()
                .log();

        StepVerifier.create(fruitsFlux)
                .expectNext("APPLE")
                .expectError(IllegalStateException.class)
                .verify();
    }

    @Test
    void fruitsFluxdoOnError() {
        var fruitsFlux=fluxAndMonoService.fruitsFluxdoOnError()
                .log();

        StepVerifier.create(fruitsFlux)
                .expectNext("APPLE")
                .expectError(RuntimeException.class)
                .verify();
    }
}