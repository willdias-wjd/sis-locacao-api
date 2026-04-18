package com.sislocacao.adapter.output.repository.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_INQUILINOS")
public class InquilinoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sobrenome;
    private String cpf;
    private String rg;
    private String telefone;
    private String email;
    private String nacionalidade;
    private String estadoCivil;
    private String profissao;
    private String genero;
    private LocalDate dataNascimento;

    private Boolean status;

    @JsonIgnore
    @OneToMany(mappedBy = "inquilino", cascade = CascadeType.ALL)
    private List<LocacaoEntity> locacoesEntity = new ArrayList<>();
}
