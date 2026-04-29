package com.sislocacao.adapter.input.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SalvarLocacaoRequest(
        @NotBlank LocalDate dataDeInicio,
        @NotBlank Integer tempoDeContrato,
        @NotBlank BigDecimal valorAluguel,
        @NotBlank Long idInquilino,
        @NotBlank Long idImovel,
        @NotBlank Long idLocador
) { }
