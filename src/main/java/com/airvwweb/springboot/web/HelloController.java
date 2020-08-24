package com.airvwweb.springboot.web;

import com.airvwweb.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
//    @RequestParam : 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션.
//    여기서는 외부에서 name(@RequestParam('name')) 이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(string name)에 저장
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){

        return new HelloResponseDto(name, amount);
    }
}