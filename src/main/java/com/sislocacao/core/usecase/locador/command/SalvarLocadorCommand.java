package com.sislocacao.core.usecase.locador.command;

import java.time.LocalDate;

public class SalvarLocadorCommand {
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
    private String logradouro;
    private String estado;
    private String bairro;
    private String cidade;
    private String cep;

    public SalvarLocadorCommand(String nome, String sobrenome, String cpf, String rg, String telefone, String email,
                                String nacionalidade, String estadoCivil, String profissao, String genero,
                                LocalDate dataNascimento, String logradouro, String estado, String bairro,
                                String cidade, String cep) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.rg = rg;
        this.telefone = telefone;
        this.email = email;
        this.nacionalidade = nacionalidade;
        this.estadoCivil = estadoCivil;
        this.profissao = profissao;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.logradouro = logradouro;
        this.estado = estado;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }

    public String getNome() { return nome; }
    public String getSobrenome() { return sobrenome; }
    public String getCpf() { return cpf; }
    public String getRg() { return rg; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public String getNacionalidade() { return nacionalidade; }
    public String getEstadoCivil() { return estadoCivil; }
    public String getProfissao() { return profissao; }
    public String getGenero() { return genero; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getLogradouro() { return logradouro; }
    public String getEstado() { return estado; }
    public String getBairro() { return bairro; }
    public String getCidade() { return cidade; }
    public String getCep() { return cep; }
}
