package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {
    @GetMapping(path="/hello")
    public String hello() {
        return "get Hello";

    }

    @RequestMapping(path = "/hi", method= RequestMethod.GET)
    public String hi() {
        return "get hi";
    }

    // Path Variable
    // http://localhost:9009/api/get/path-variable/{name}
    @GetMapping("/path-variable/{id}")
    //public String pathVariable(@PathVariable String id) { // URI {name} 과 파라미터의 name이 동일해야함
    public String pathVariable(@PathVariable(name="id") String pathId) { // URI {name} 과 파라미터의 name이 동일하지 않은 경우
        System.out.println("Path variable : " +pathId);
        return pathId;
    }

    // Query Parameter - URL에 ? 뒷부분, 검색할 때 사용
    // http://localhost:9090/api/get/query-param?name=steve&email=abc@def.com
    // 각 변수가 지정되지 않은 경우
    @GetMapping(path="/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParma) {
        StringBuilder sb = new StringBuilder();
        queryParma.entrySet().forEach(entry ->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        });

        return sb.toString();
    }

    // 각 변수가 지정된 경우
    @GetMapping(path="/query-param02")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age
    ) {
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);
        return name + " " + email + " " + age;
    }

    // DTO 형태로 Mapping 가능
    // RequestParam 어노테이션을 붙이지 않는다.
    @GetMapping(path="/query-param03")
    public String queryParam02(UserRequest userRequest) {
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());
        return userRequest.toString();
    }
}
