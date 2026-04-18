package com.sislocacao.adapter.input.dto.request;

import java.time.LocalDate;

public record SalvarInquilinoRequest(
        String nome,
        String sobrenome,
        String cpf,
        String rg,
        String telefone,
        String email,
        String nacionalidade,
        String estadoCivil,
        String profissao,
        String genero,
        LocalDate dataNascimento
) { }
