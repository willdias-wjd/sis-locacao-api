package com.sislocacao.adapter.output.repository.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_IMOVEIS")
public class ImovelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Boolean garagem;
    private Integer comodos;
    private String numero;

    private Boolean status;

    @JsonIgnore
    @OneToMany(mappedBy = "imovel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LocacaoEntity> locacoes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private EnderecoEntity endereco;
}
