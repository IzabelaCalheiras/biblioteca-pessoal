package com.biblioteca.biblioteca_pessoal.dto;

import com.biblioteca.biblioteca_pessoal.model.Livro;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados completos de um livro")
public record LivroResponse(

    @Schema(description = "ID do livro", example = "1")
    Long id,

    @Schema(description = "Título do livro", example = "O Senhor dos Anéis")
    String titulo,

    @Schema(description = "Nome do autor", example = "J.R.R. Tolkien")
    String autor,

    @Schema(description = "Código ISBN", example = "978-8595084759")
    String isbn,

    @Schema(description = "Ano de publicação", example = "1954")
    Integer ano,

    @Schema(description = "Status de leitura", example = "false")
    Boolean lido
) {
    // Método estático para converter a Entidade em DTO de Saída
    public static LivroResponse from(Livro livro) {
        return new LivroResponse(
            livro.getId(),
            livro.getTitulo(),
            livro.getAutor(),
            livro.getIsbn(),
            livro.getAno(),
            livro.getLido()
        );
    }
}