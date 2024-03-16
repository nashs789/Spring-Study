package com.redis.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.redis.entity.Member;
import com.redis.token.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final RedisUtils redisUtils;

    @GetMapping("/hello")
    public void get() {
        String test = "Test";

        Member member1 = new Member(1L);
        Member member2 = new Member(2L);

        List<Member> list = new ArrayList<>();
        list.add(member1);
        list.add(member2);

        redisUtils.setObjectData("list", list);

        List<Member> findList = redisUtils.getObjectData("list", new TypeReference<List<Member>>() {}).get();

        for (Member member : findList) {
            System.out.println(member.getId());
        }
    }
}
