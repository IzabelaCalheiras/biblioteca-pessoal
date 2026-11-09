package com.biblioteca.biblioteca_pessoal.mapper;

import com.biblioteca.biblioteca_pessoal.dto.LivroRequest;
import com.biblioteca.biblioteca_pessoal.dto.LivroResponse;
import com.biblioteca.biblioteca_pessoal.model.Livro;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivroMapper {

    /** Converte DTO de entrada em Entidade (CREATE) */
    public Livro toEntity(LivroRequest request) {
        Livro livro = new Livro();
        livro.setTitulo(request.titulo());
        livro.setAutor(request.autor());
        livro.setIsbn(request.isbn());
        livro.setAno(request.ano());
        livro.setLido(false);
        return livro;
    }

    /** Aplica os dados do DTO em uma entidade existente (UPDATE) */
    public void updateEntity(LivroRequest request, Livro livro) {
        livro.setTitulo(request.titulo());
        livro.setAutor(request.autor());
        livro.setIsbn(request.isbn());
        livro.setAno(request.ano());
    }

    /** Converte Entidade em DTO de saída */
    public LivroResponse toResponse(Livro livro) {
        return LivroResponse.from(livro);
    }

    /** Converte lista de Entidades em lista de DTOs */
    public List<LivroResponse> toResponseList(List<Livro> livros) {
        return livros.stream().map(this::toResponse).toList();
    }
}