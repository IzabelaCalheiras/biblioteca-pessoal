package com.biblioteca.biblioteca_pessoal.controller;

import com.biblioteca.biblioteca_pessoal.dto.LivroRequest;
import com.biblioteca.biblioteca_pessoal.dto.LivroResponse;
import com.biblioteca.biblioteca_pessoal.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
@Tag(name = "Livros", description = "Gerenciamento de livros da biblioteca")
public class LivroRestController {

    private final LivroService service;

    public LivroRestController(LivroService service) {
        this.service = service;
    }

    @Operation(summary = "Lista todos os livros")
    @GetMapping
    public ResponseEntity<List<LivroResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Busca livro por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Livro encontrado"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> buscar(
            @Parameter(description = "ID do livro", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Lista livros não lidos")
    @GetMapping("/nao-lidos")
    public ResponseEntity<List<LivroResponse>> naoLidos() {
        return ResponseEntity.ok(service.listarNaoLidos());
    }

    @Operation(summary = "Lista livros lidos")
    @GetMapping("/lidos")
    public ResponseEntity<List<LivroResponse>> lidos() {
        return ResponseEntity.ok(service.listarLidos());
    }

    @Operation(summary = "Busca livros por título")
    @GetMapping("/busca/titulo")
    public ResponseEntity<List<LivroResponse>> buscarPorTitulo(
            @Parameter(description = "Trecho do título", example = "Senhor")
            @RequestParam String termo) {
        return ResponseEntity.ok(service.buscarPorTitulo(termo));
    }

    @Operation(summary = "Busca livros por autor")
    @GetMapping("/busca/autor")
    public ResponseEntity<List<LivroResponse>> buscarPorAutor(
            @Parameter(description = "Nome do autor", example = "Tolkien")
            @RequestParam String termo) {
        return ResponseEntity.ok(service.buscarPorAutor(termo));
    }

    @Operation(summary = "Cadastra um novo livro")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Livro criado"),
        @ApiResponse(responseCode = "400", description = "Erro de validação")
    })
    @PostMapping
    public ResponseEntity<LivroResponse> criar(@Valid @RequestBody LivroRequest request) {
        LivroResponse response = service.criar(request);
        URI location = URI.create("/api/livros/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Atualiza um livro existente")
    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody LivroRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @Operation(summary = "Marca livro como lido")
    @PatchMapping("/{id}/lido")
    public ResponseEntity<LivroResponse> marcarLido(@PathVariable Long id) {
        return ResponseEntity.ok(service.marcarComoLido(id));
    }

    @Operation(summary = "Exclui um livro")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Livro excluído"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}