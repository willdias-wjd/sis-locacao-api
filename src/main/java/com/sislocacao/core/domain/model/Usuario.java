package com.sislocacao.core.domain.model;

import com.sislocacao.core.domain.enums.Role;

public class Usuario {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private Boolean ativo;
    private Role role;

    public Usuario(Long id, String nome, String sobrenome, String email, String senha, Role role) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.ativo = Boolean.TRUE;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Role getRole() {
        return role;
    }
}
