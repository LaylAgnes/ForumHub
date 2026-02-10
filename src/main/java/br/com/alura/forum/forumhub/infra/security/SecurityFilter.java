package br.com.alura.forum.forumhub.infra.security;

import br.com.alura.forum.forumhub.infra.security.TokenService;
import br.com.alura.forum.forumhub.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            try {
                String login = tokenService.getSubject(tokenJWT);

                var usuario = usuarioRepository.findByLogin(login)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

                var authentication = new UsernamePasswordAuthenticationToken(
                        usuario,                     // principal
                        null,                        // credentials
                        usuario.getAuthorities()     // authorities (OBRIGATÓRIO)
                );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                // Token inválido, expirado ou usuário inexistente
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }

        return null;
    }
}
