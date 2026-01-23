package me.oguzhanozer.musfer.apigateway.controller;

import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public Mono<String> getHello() {
        return Mono.just("hello");
    }

}
