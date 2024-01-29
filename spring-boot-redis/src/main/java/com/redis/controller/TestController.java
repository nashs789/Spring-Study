package com.redis.controller;

import com.redis.token.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final RedisUtils redisUtils;

    @GetMapping("/hello")
    public void get() {
        String test = "Test";

        redisUtils.setData(test, "sfgnkdfls");
    }
}
