package org.msa.msa5spring.error;

import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponseDTO<Void>> handle(Exception e){
        return ResponseEntity.status(500).body(new GlobalResponseDTO<>("E99", "시스템 에러", null));
    }

}
//@ExceptionHandler는 (Exception.class)<-안에 예외가 발생하면
//public ResponseEntity<GlobalResponseDTO<Void>> handle(){} 이 실행하겠다는 메소드
// .class 쓰는 이유? 자바에서 Exception만 쓰면 데이터타입으로 읽는다.  .class로 쓰면  인스턴스(해당 객체:인스턴스화가 끝내야 인스턴스라고 한다.)를 의미한다. (메모리에 올라가서 메소드로 쓸수있는 상태)
// 런타임? 도중에 어노테이션한테 타입만 주면 모르기때문에 인스턴스형태로 줘야 알수있기때문에
//가져온 예외랑 Exception가 같으면 밑에가 실행된다.
//객체명.class