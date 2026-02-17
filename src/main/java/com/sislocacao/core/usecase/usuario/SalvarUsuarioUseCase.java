package com.sislocacao.core.usecase.usuario;

import com.sislocacao.core.common.DomainComponent;
import com.sislocacao.core.domain.enums.Role;
import com.sislocacao.core.domain.model.Usuario;
import com.sislocacao.core.exception.BusinessException;
import com.sislocacao.core.repository.IUsuarioRepository;
import com.sislocacao.ports.input.SalvarUsuarioInputPort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@DomainComponent
public class SalvarUsuarioUseCase implements SalvarUsuarioInputPort {

    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public SalvarUsuarioUseCase(IUsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario executar(Usuario usuario) {
        Optional<Usuario> checkUsuarioExistente = usuarioRepository.buscarPorEmail(usuario.getEmail());

        if(checkUsuarioExistente.isPresent()) {
            throw new BusinessException("Já existe um usuário cadastrado com o e-mail informado.");
        }

        var passwordHash = passwordEncoder.encode(usuario.getSenha());

        Usuario usuarioCriado = new Usuario(
                null,
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getEmail(),
                passwordHash,
                Role.ADMIN
        );

        return usuarioRepository.salvarUsuario(usuarioCriado);
    }
}
