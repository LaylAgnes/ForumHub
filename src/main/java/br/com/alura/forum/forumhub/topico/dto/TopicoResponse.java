package br.com.alura.forum.forumhub.topico.dto;

import br.com.alura.forum.forumhub.topico.Topico;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TopicoResponse {

    private final Long id;
    private final String titulo;
    private final String mensagem;
    private final LocalDateTime dataCriacao;
    private final String status;
    private final String autor;
    private final String curso;

    public TopicoResponse(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.status = topico.getStatus();
        this.autor = topico.getAutor();
        this.curso = topico.getCurso();
    }

}
