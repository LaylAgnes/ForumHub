package br.com.alura.forum.forumhub.usuario;

import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;

    public AutenticacaoController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public void login(@RequestBody @Valid DadosAutenticacao dados) {
        Authentication token = new UsernamePasswordAuthenticationToken(
                dados.login(),
                dados.senha()
        );

        authenticationManager.authenticate(token);
    }
}
