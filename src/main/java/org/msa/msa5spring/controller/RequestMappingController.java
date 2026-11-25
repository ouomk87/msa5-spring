package org.msa.msa5spring.controller;

import org.msa.msa5spring.request.QueryRequestDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.invoke.MutableCallSite;

@RestController
@RequestMapping("/api")
//@RestController는 이 클래스가 웹 요청을 처리하는 컨트롤러라고 스프링에 알리는 어노테이션이다.
public class RequestMappingController {
    //메소드 뒤에 어노테이션 넣어줌 자원은 users이고 이메소드로 들어가서 처리를 해준다.
    //스프링부트는 8080포트로 열림 http://localhost:8080/users 서버를 끄면 연결도 꺼짐/코드를 수정했으면 재실행해야함
    @GetMapping
    public String index(){
        return "유저 조회";

    }
    @PostMapping // 이렇게 하면 get/users/1 갔기 때문에 페이지가 안 나옴
    public  String store(){
        return "유저 생성";
    }

    @PutMapping
    public String update(){
        return "유저 수정";
    }
    @DeleteMapping
    public String destroy(){
        return "유저 삭제";
    }
    @GetMapping("/query")
    public String query(QueryRequestDTO queryRequestDTO){
    return String.format("%d : %s", queryRequestDTO.id(), queryRequestDTO.name());
    }
    @GetMapping("/segment/{id}/{name}") // {key} segment도 여러개 넣어둬됨
    public String segment(@ModelAttribute QueryRequestDTO queryRequestDTO){
        return String.format("segment = %d : %s", queryRequestDTO.id(), queryRequestDTO.name());

    }
    @PostMapping("/form")
    public String form(@ModelAttribute MultipartFile file){
        return String.format("%s :%s", file.getContentType(), file.getOriginalFilename());

    }

    @GetMapping("/json")
    public String json(@RequestBody QueryRequestDTO queryRequestDTO){
        return String.format("json = %d : %s", queryRequestDTO.id(), queryRequestDTO.name());

    }


}
// Query Parameter
// - 어노테이션 X, DTO 객체 생성

// Segment Parameter 방식
// -@PathVariable, 각 키에 맞는 파라미터를 별도로 맴핑 메소드의  파라미터로 생성
// -@ModelAttribute, DTO객체 생성

//Form Data 방식
// -@ModelAttribute, DTO객체 또는 컨트롤러 파라미터

//JSON 방식
//@RequestBody, DRO객체
