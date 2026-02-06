package br.com.alura.forum.forumhub.topico.controller;

import br.com.alura.forum.forumhub.topico.Topico;
import br.com.alura.forum.forumhub.topico.dto.TopicoRequest;
import br.com.alura.forum.forumhub.topico.dto.TopicoUpdateRequest;
import br.com.alura.forum.forumhub.topico.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.alura.forum.forumhub.topico.dto.TopicoResponse;
import java.util.List;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService service;

    public TopicoController(TopicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Topico> cadastrar(
            @RequestBody @Valid TopicoRequest request
    ) {
        Topico topico = service.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }

    @GetMapping
    public List<TopicoResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public TopicoResponse detalhar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid TopicoUpdateRequest request
    ) {
        TopicoResponse response = service.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
