package org.msa.msa5spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RestController는 이 클래스가 웹 요청을 처리하는 컨트롤러라고 스프링에 알리는 어노테이션이다.
public class RequestMappingController {
    //메소드 뒤에 어노테이션 넣어줌 자원은 users이고 이메소드로 들어가서 처리를 해준다.
    //스프링부트는 8080포트로 열림 http://localhost:8080/users 서버를 끄면 연결도 꺼짐/코드를 수정했으면 재실행해야함
    @GetMapping("/users")
    public String index(){
        return "유저 조회";

    }
    @PostMapping("/users/1") // 이렇게 하면 get/users/1 갔기 때문에 페이지가 안 나옴
    public  String store(){
        return "유저 생성";
    }
}
