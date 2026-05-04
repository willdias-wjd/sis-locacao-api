package com.sislocacao.adapter.input.dto.request;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequest(
        @NotBlank String logradouro,
        @NotBlank String estado,
        @NotBlank String bairro,
        @NotBlank String cidade,
        @NotBlank String cep
) {}
