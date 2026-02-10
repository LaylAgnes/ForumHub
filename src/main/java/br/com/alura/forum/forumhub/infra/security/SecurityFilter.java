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

        System.out.println("=== SECURITY FILTER ===");
        System.out.println("Authorization header: " + request.getHeader("Authorization"));

        String tokenJWT = recuperarToken(request);
        System.out.println("Token extraído: " + tokenJWT);

        if (tokenJWT != null) {
            try {
                String login = tokenService.getSubject(tokenJWT);
                System.out.println("Login do token: " + login);

                var usuario = usuarioRepository.findByLogin(login)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

                System.out.println("Authorities do usuário: " + usuario.getAuthorities());

                var authentication = new UsernamePasswordAuthenticationToken(
                        usuario,
                        null,
                        usuario.getAuthorities()
                );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                System.out.println("ERRO NO FILTRO: " + e.getMessage());
                SecurityContextHolder.clearContext();
            }
        }

        System.out.println(
                "Authentication no contexto: "
                        + SecurityContextHolder.getContext().getAuthentication()
        );

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null) return null;

        String[] parts = header.split(" ");

        if (parts.length != 2) return null;

        return parts[1].trim();
    }


}
