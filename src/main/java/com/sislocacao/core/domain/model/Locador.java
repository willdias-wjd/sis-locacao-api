package com.sislocacao.core.domain.model;

import java.time.LocalDate;

public class Locador {
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
    private Endereco endereco;

    // Construtor all-args mantido para o MapStruct
    public Locador(Long id, String nome, String sobrenome, String cpf, String rg, String telefone, String email,
                   String nacionalidade, String estadoCivil, String profissao, String genero,
                   LocalDate dataNascimento, Boolean status, Endereco endereco) {
        this.id = id;
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
        this.status = status;
        this.endereco = endereco;
    }

    public Locador inativar() {
        return new Locador(id, nome, sobrenome, cpf, rg, telefone, email,
                nacionalidade, estadoCivil, profissao, genero, dataNascimento, Boolean.FALSE, endereco);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() { return id; }
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
    public Boolean getStatus() { return status; }
    public Endereco getEndereco() { return endereco; }

    public static class Builder {
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
        private Endereco endereco;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder nome(String nome) { this.nome = nome; return this; }
        public Builder sobrenome(String sobrenome) { this.sobrenome = sobrenome; return this; }
        public Builder cpf(String cpf) { this.cpf = cpf; return this; }
        public Builder rg(String rg) { this.rg = rg; return this; }
        public Builder telefone(String telefone) { this.telefone = telefone; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder nacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; return this; }
        public Builder estadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; return this; }
        public Builder profissao(String profissao) { this.profissao = profissao; return this; }
        public Builder genero(String genero) { this.genero = genero; return this; }
        public Builder dataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; return this; }
        public Builder status(Boolean status) { this.status = status; return this; }
        public Builder endereco(Endereco endereco) { this.endereco = endereco; return this; }

        public Locador build() {
            return new Locador(id, nome, sobrenome, cpf, rg, telefone, email,
                    nacionalidade, estadoCivil, profissao, genero, dataNascimento, status, endereco);
        }
    }
}
