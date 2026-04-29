package com.sislocacao.core.usecase.imovel.command;

public class SalvarImovelCommand {
    private String descricao;
    private Boolean garagem;
    private Integer comodos;
    private String numero;
    private Long enderecoId;

    public SalvarImovelCommand(String descricao, Boolean garagem, Integer comodos, String numero, Long enderecoId) {
        this.descricao = descricao;
        this.garagem = garagem;
        this.comodos = comodos;
        this.numero = numero;
        this.enderecoId = enderecoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getGaragem() {
        return garagem;
    }

    public void setGaragem(Boolean garagem) {
        this.garagem = garagem;
    }

    public Integer getComodos() {
        return comodos;
    }

    public void setComodos(Integer comodos) {
        this.comodos = comodos;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }
}
