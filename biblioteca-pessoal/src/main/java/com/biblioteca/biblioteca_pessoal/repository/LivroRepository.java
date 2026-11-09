package com.biblioteca.biblioteca_pessoal.repository;

import com.biblioteca.biblioteca_pessoal.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Derived Queries exigidas no trabalho
    List<Livro> findByLidoFalse();
    List<Livro> findByLidoTrue();
    List<Livro> findByTituloContainingIgnoreCase(String termo);
    List<Livro> findByAutorContainingIgnoreCase(String termo);
}