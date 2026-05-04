package com.sislocacao.core.usecase.imovel.command;

public class EnderecoCommand {
    private String logradouro;
    private String estado;
    private String bairro;
    private String cidade;
    private String cep;

    public EnderecoCommand(String logradouro, String estado, String bairro, String cidade, String cep) {
        this.logradouro = logradouro;
        this.estado = estado;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }

    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
}
