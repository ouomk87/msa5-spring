package org.msa.msa5spring.request;

public record QueryRequestDTO(
        Long id, //객체 Long으로 안 보내고
        String name
) {}
