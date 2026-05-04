package com.sislocacao.adapter.input.dto.response;

public record EnderecoResponse(
        String logradouro,
        String estado,
        String bairro,
        String cidade,
        String cep
) { }
