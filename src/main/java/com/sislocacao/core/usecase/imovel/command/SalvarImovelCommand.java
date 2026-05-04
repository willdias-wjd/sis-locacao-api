package com.sislocacao.core.usecase.imovel.command;

public class SalvarImovelCommand {
    private String descricao;
    private Boolean garagem;
    private Integer comodos;
    private String numero;
    private EnderecoCommand endereco;

    public SalvarImovelCommand(String descricao, Boolean garagem, Integer comodos, String numero, EnderecoCommand endereco) {
        this.descricao = descricao;
        this.garagem = garagem;
        this.comodos = comodos;
        this.numero = numero;
        this.endereco = endereco;
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Boolean getGaragem() { return garagem; }
    public void setGaragem(Boolean garagem) { this.garagem = garagem; }

    public Integer getComodos() { return comodos; }
    public void setComodos(Integer comodos) { this.comodos = comodos; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public EnderecoCommand getEndereco() { return endereco; }
    public void setEndereco(EnderecoCommand endereco) { this.endereco = endereco; }
}
