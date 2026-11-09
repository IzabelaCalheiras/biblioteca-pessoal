package com.biblioteca.biblioteca_pessoal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para criação ou atualização de um livro")
public record LivroRequest(

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 2, max = 150, message = "O título deve ter entre 2 e 150 caracteres")
    @Schema(description = "Título do livro", example = "O Senhor dos Anéis")
    String titulo,

    @NotBlank(message = "O autor é obrigatório")
    @Size(min = 2, max = 100, message = "O autor deve ter entre 2 e 100 caracteres")
    @Schema(description = "Nome do autor", example = "J.R.R. Tolkien")
    String autor,

    @Size(max = 20, message = "O ISBN deve ter no máximo 20 caracteres")
    @Schema(description = "Código ISBN", example = "978-8595084759")
    String isbn,

    @Schema(description = "Ano de publicação", example = "1954")
    Integer ano
) {}