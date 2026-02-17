package com.sislocacao.core.usecase.usuario;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.model.Usuario;
import com.sislocacao.core.repository.IUsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@DomainComponent
public class LoginUseCase {
    private final IUsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public LoginUseCase(IUsuarioRepository repository,
                        PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario executar(String email, String senha) {

        Usuario usuario = repository.buscarPorEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        return usuario;
    }
}
