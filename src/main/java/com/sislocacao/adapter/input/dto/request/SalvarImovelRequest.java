package com.sislocacao.adapter.input.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SalvarImovelRequest(
        @NotBlank String descricao,
        @NotNull Boolean garagem,
        @NotNull Integer comodos,
        @NotBlank String numero,
        @NotNull EnderecoRequest endereco
) { }
