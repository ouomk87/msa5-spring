package org.msa.msa5spring.response;

public record GlobalResponseDTO<T>(
        String code,
        String message,
        T result //요청에 따라 데이터 타입이 달라짐 레네릭을 사용한다.
) {
}
