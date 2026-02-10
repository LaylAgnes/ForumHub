package br.com.alura.forum.forumhub.topico.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TopicoRequest {
    @NotBlank
    private String titulo;

    @NotBlank
    private String mensagem;

    @NotBlank
    private String autor;

    @NotBlank
    private String curso;

    public TopicoRequest(String titulo) {
        this.titulo = titulo;
    }
}
