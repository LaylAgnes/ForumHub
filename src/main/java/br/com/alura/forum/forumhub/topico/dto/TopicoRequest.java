package br.com.alura.forum.forumhub.topico.dto;

import jakarta.validation.constraints.NotBlank;

public class TopicoRequest {
    @NotBlank
    private String titulo;

    @NotBlank
    private String mensagem;

    @NotBlank
    private String autor;

    @NotBlank
    private String curso;

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }
}
