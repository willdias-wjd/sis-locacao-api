package com.sislocacao.adapter.input.controllers;

import com.sislocacao.adapter.input.dto.request.LoginRequest;
import com.sislocacao.adapter.input.dto.response.LoginResponse;
import com.sislocacao.adapter.security.JwtService;
import com.sislocacao.core.domain.model.Usuario;
import com.sislocacao.core.usecase.usuario.LoginUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final JwtService jwtService;

    public AuthController(LoginUseCase loginUseCase,
                          JwtService jwtService) {
        this.loginUseCase = loginUseCase;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        Usuario usuario = loginUseCase.executar(
                request.email(),
                request.senha()
        );

        String token = jwtService.gerarToken(usuario);

        return new LoginResponse(
                token,
                usuario.getId(),
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getEmail()
        );
    }
}