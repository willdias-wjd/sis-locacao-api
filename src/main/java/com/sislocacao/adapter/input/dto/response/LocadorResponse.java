package com.sislocacao.adapter.input.dto.response;

public record LocadorResponse(
        Long id,
        String nome,
        String sobrenome,
        String telefone,
        String email
) { }
