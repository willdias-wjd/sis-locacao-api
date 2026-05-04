package com.sislocacao.adapter.input.dto.response;

import java.time.LocalDate;

public record LocadorResponse(
        Long id,
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
        LocalDate dataNascimento,
        Boolean status,
        EnderecoResponse endereco
) { }
