package org.msa.msa5spring.request;

import jakarta.validation.constraints.*;

public record RegistrationRequestDTO(

        @NotBlank(message = "이메일 필수")
        @Pattern(regexp = "^[a-z]{4,8}$", message = "이메일 패턴 틀림") // NotBlank보다 우선시 된다
        String email,

        @NotBlank(message = "비밀번호 필수")
        @Size(min = 2, max = 10, message = "비밀번호는 2~10자")
        String password,

        String name,

        @Min(value = 0, message = "나이는 최소 0살입니다.")
        @Max(value = 999, message = "나이는 최대 999살까지 입니다.")
        Integer age
) {}


//Pattern을 제일 많이 사용함
//편한걸로 쓰되 검증만 잘하면된다
