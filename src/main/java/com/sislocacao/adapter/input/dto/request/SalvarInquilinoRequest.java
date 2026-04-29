package com.sislocacao.adapter.input.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record SalvarInquilinoRequest(
        @NotBlank(message = "O campo nome é obrigatório")
        String nome,

        @NotBlank(message = "O campo sobrenome é obrigatório")
        String sobrenome,

        @NotBlank(message = "O campo cpf é obrigatório")
        @Pattern(
                regexp = "(^\\d{11}$)|(^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$)",
                message = "CPF inválido"
        )
        String cpf,

        @NotBlank(message = "O campo rg é obrigatório")
        String rg,

        String telefone,

        @Email(message = "E-mail deve estar em um formato válido")
        String email,

        @NotBlank(message = "O campo nacionalidade é obrigatório")
        String nacionalidade,

        @NotBlank(message = "O campo estadoCivil é obrigatório")
        String estadoCivil,

        String profissao,
        String genero,

        @PastOrPresent(message = "Data de nascimento não pode ser futura")
        LocalDate dataNascimento
) { }
