package org.msa.msa5spring.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.msa.msa5spring.request.RegistrationRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ValidationController {
    @GetMapping("/validation")
    public String validation(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO){
        return "유효성 검사";
    }
    @GetMapping("/validation/{id}")
    public String validationSegment(
          @Min(value = 1, message = "1이상")  @PathVariable Long id
    ){ //간단한 처리는 DTO안 만들고 여기에 적어도 된다.
        return id.toString();//Long타입을 문자열로 반환;
    }
}
