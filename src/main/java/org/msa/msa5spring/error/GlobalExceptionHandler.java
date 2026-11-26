
package org.msa.msa5spring.error;

import lombok.extern.slf4j.Slf4j;
import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //------------------------------------------------------------------
    // 유효성 체크 예외 관련
    @ExceptionHandler(MethodArgumentTypeMismatchException.class) // 핸들링하고 싶은 Exception을 쓰면된다.
    public ResponseEntity<GlobalResponseDTO<String>> handle(MethodArgumentTypeMismatchException e) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new GlobalResponseDTO<>(
                                "E01",
                                "유효성 체크 에러",
                                String.format("%s:필드를 확인해 주세요.", e.getName()) //getName 해당 필드명이 들어간다.
                        )
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponseDTO<Map<String, String>>> handle(MethodArgumentNotValidException e){
        Map<String, String> errors = e.getBindingResult()
                .getFieldErrors()
                // [o] 필드 에러 [1]에도 필드 에러가 들어가있는데 필드에러가 하나 들어왔을때
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField, //필드명을 가져와서 map의 key로 사용
                        (fieldError) -> fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "유효하지 않은 값입니다.",
                        //키 ,키에 담을 값(벨루), 중복된걸 줬을때 어떻게 처리할지를 적는다. 보통 이렇게 3가지 적는다.
                        (existing, replacement) -> existing
                        //existing기존값 , replacement 새로운값
                )); //최종연산메소드
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new GlobalResponseDTO<>(
                                "E01",
                                "유효성 체크 에러",
                                errors
                        )
                );

    }

    //------------------------------------------------------------------
    // 그외 모든 예외 처리 핸드러 메소드


    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponseDTO<Void>> handle(Exception e) {
        log.trace("트레이스 로깅");
        log.debug("디버그 로깅");
        log.info("인포 로깅");
        log.warn("워닝 로깅");
        log.error("에러 로깅");


        return ResponseEntity.status(500).body(new GlobalResponseDTO<>("E99", "시스템 에러", null));
    }

}

//@ExceptionHandler는 (Exception.class)<-안에 예외가 발생하면
//public ResponseEntity<GlobalResponseDTO<Void>> handle(){} 이 실행하겠다는 메소드
// .class 쓰는 이유? 자바에서 Exception만 쓰면 데이터타입으로 읽는다.  .class로 쓰면  인스턴스(해당 객체:인스턴스화가 끝내야 인스턴스라고 한다.)를 의미한다. (메모리에 올라가서 메소드로 쓸수있는 상태)
// 런타임? 도중에 어노테이션한테 타입만 주면 모르기때문에 인스턴스형태로 줘야 알수있기때문에
//가져온 예외랑 Exception가 같으면 밑에가 실행된다.
//객체명.class