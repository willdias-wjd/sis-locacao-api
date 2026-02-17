package com.sislocacao.adapter.input.dto.response;

public record LoginResponse(
        String token,
        Long id,
        String nome,
        String sobrenome,
        String email
) {}