package br.com.alura.forum.forumhub.topico;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "topicos")
public class Topico {

    // 🔹 GETTERS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 SETTERS (ESSENCIAIS PARA O PUT)
    @Setter
    @Column(nullable = false)
    private String titulo;

    @Setter
    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private String status;

    @Setter
    @Column(nullable = false)
    private String autor;

    @Setter
    @Column(nullable = false)
    private String curso;

    // 🔹 Construtor padrão (JPA)
    public Topico() {
    }

    // 🔹 Construtor de criação
    public Topico(String titulo, String mensagem, String autor, String curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.curso = curso;
        this.status = "ABERTO";
        this.dataCriacao = LocalDateTime.now();
    }

}
