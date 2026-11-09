package com.biblioteca.biblioteca_pessoal.loader;

import com.biblioteca.biblioteca_pessoal.model.Livro;
import com.biblioteca.biblioteca_pessoal.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final LivroRepository repository;

    public DataLoader(LivroRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            repository.saveAll(List.of(
                    new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "978-8595084759", 1954),
                    new Livro("1984", "George Orwell", "978-8535914849", 1949),
                    new Livro("Dom Casmurro", "Machado de Assis", "978-8572327072", 1899)
            ));
            System.out.println(">> Livros de exemplo carregados no banco H2!");
        }
    }
}