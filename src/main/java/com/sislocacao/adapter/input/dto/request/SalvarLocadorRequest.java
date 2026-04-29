package com.sislocacao.adapter.input.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record SalvarLocadorRequest(
        @NotBlank String nome,
        @NotBlank String sobrenome,
        @NotBlank String cpf,
        @NotBlank String rg,
        String telefone,
        String email,
        @NotBlank String nacionalidade,
        @NotBlank String estadoCivil,
        String profissao,
        String genero,
        LocalDate dataNascimento,
        @NotBlank String logradouro,
        @NotBlank String estado,
        @NotBlank String bairro,
        @NotBlank String cidade,
        @NotBlank String cep
) { }
