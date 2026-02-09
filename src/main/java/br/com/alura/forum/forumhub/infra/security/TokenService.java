package br.com.alura.forum.forumhub.infra.security;

import br.com.alura.forum.forumhub.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("ForumHub API")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar token JWT", e);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now()
                .plusSeconds(expiration / 1000)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
