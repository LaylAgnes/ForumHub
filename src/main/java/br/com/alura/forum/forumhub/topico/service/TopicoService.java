package br.com.alura.forum.forumhub.topico.service;

import br.com.alura.forum.forumhub.topico.Topico;
import br.com.alura.forum.forumhub.topico.dto.TopicoRequest;
import br.com.alura.forum.forumhub.topico.dto.TopicoResponse;
import br.com.alura.forum.forumhub.topico.dto.TopicoUpdateRequest;
import br.com.alura.forum.forumhub.topico.repository.TopicoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TopicoService {

    private final TopicoRepository repository;

    public TopicoService(TopicoRepository repository) {
        this.repository = repository;
    }

    // =========================
    // CADASTRO
    // =========================
    public Topico cadastrar(TopicoRequest request) {

        boolean existe = repository.existsByTituloAndMensagem(
                request.getTitulo(),
                request.getMensagem()
        );

        if (existe) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe um tópico com o mesmo título e mensagem"
            );

        }

        Topico topico = new Topico(
                request.getTitulo(),
                request.getMensagem(),
                request.getAutor(),
                request.getCurso()
        );

        return repository.save(topico);
    }

    // =========================
    // LISTAGEM
    // =========================
    public List<TopicoResponse> listar() {
        return repository.findAll()
                .stream()
                .map(TopicoResponse::new)
                .toList();
    }

    // =========================
    // DETALHAMENTO POR ID
    // =========================
    public TopicoResponse buscarPorId(Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Tópico não encontrado"
                        )
                );

        return new TopicoResponse(topico);
    }

    // =========================
    // ATUALIZAÇÃO (PUT)
    // =========================
    public TopicoResponse atualizar(Long id, TopicoUpdateRequest request) {

        Topico topico = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Tópico não encontrado"
                        )
                );

        boolean existeDuplicado =
                repository.existsByTituloAndMensagemAndIdNot(
                        request.getTitulo(),
                        request.getMensagem(),
                        id
                );

        if (existeDuplicado) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe um tópico com o mesmo título e mensagem"
            );
        }

        topico.setTitulo(request.getTitulo());
        topico.setMensagem(request.getMensagem());
        topico.setAutor(request.getAutor());
        topico.setCurso(request.getCurso());

        return new TopicoResponse(repository.save(topico));
    }

    // =========================
    // DELETAR (DELETE)
    // =========================

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Tópico não encontrado"
            );
        }

        repository.deleteById(id);
    }


}
