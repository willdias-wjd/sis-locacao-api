package com.sislocacao.core.domain.model;

import java.time.LocalDateTime;

public class TipoContrato {
    private Long id;
    private String nome;
    private String descricao;
    private String nomeArquivo;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public TipoContrato(Long id, String nome, String descricao, String nomeArquivo, Boolean ativo, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.nomeArquivo = nomeArquivo;
        this.ativo = ativo;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public static TipoContrato criarTipoContrato(Long id, String nome, String descricao, String nomeArquivo, Boolean ativo, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        return new TipoContrato(id, nome, descricao, nomeArquivo, ativo, dataCriacao, dataAtualizacao);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getdataCriacao() {
        return dataCriacao;
    }

    public void setdataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getdataAtualizacao() {
        return dataAtualizacao;
    }

    public void setdataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
