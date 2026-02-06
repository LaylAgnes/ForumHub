package br.com.alura.forum.forumhub.topico.repository;

import br.com.alura.forum.forumhub.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensagemAndIdNot(
            String titulo,
            String mensagem,
            Long id
    );

    boolean existsByTituloAndMensagem(String titulo, String mensagem);
}
