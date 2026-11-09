package com.biblioteca.biblioteca_pessoal.service;

import com.biblioteca.biblioteca_pessoal.dto.LivroRequest;
import com.biblioteca.biblioteca_pessoal.dto.LivroResponse;
import com.biblioteca.biblioteca_pessoal.exception.RecursoNaoEncontradoException;
import com.biblioteca.biblioteca_pessoal.exception.RegraNegocioException;
import com.biblioteca.biblioteca_pessoal.mapper.LivroMapper;
import com.biblioteca.biblioteca_pessoal.model.Livro;
import com.biblioteca.biblioteca_pessoal.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository repository;
    private final LivroMapper mapper;

    public LivroService(LivroRepository repository, LivroMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // == READ ==
    @Transactional(readOnly = true)
    public List<LivroResponse> listarTodos() {
        return mapper.toResponseList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public LivroResponse buscarPorId(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro com ID " + id + " não existe"));
        return mapper.toResponse(livro);
    }

    @Transactional(readOnly = true)
    public List<LivroResponse> listarNaoLidos() {
        return mapper.toResponseList(repository.findByLidoFalse());
    }

    @Transactional(readOnly = true)
    public List<LivroResponse> listarLidos() {
        return mapper.toResponseList(repository.findByLidoTrue());
    }

    @Transactional(readOnly = true)
    public List<LivroResponse> buscarPorTitulo(String termo) {
        return mapper.toResponseList(repository.findByTituloContainingIgnoreCase(termo));
    }

    @Transactional(readOnly = true)
    public List<LivroResponse> buscarPorAutor(String termo) {
        return mapper.toResponseList(repository.findByAutorContainingIgnoreCase(termo));
    }

    // == CREATE ==
    @Transactional
    public LivroResponse criar(LivroRequest request) {
        Livro livro = mapper.toEntity(request);
        Livro salvo = repository.save(livro);
        return mapper.toResponse(salvo);
    }

    // == UPDATE ==
    @Transactional
    public LivroResponse atualizar(Long id, LivroRequest request) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro com ID " + id + " não existe"));
        
        mapper.updateEntity(request, livro);
        Livro atualizado = repository.save(livro);
        return mapper.toResponse(atualizado);
    }

    @Transactional
    public LivroResponse marcarComoLido(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro com ID " + id + " não existe"));
        
        if (Boolean.TRUE.equals(livro.getLido())) {
            throw new RegraNegocioException("O livro já está marcado como lido");
        }
        
        livro.setLido(true);
        return mapper.toResponse(repository.save(livro));
    }

    // == DELETE ==
    @Transactional
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Livro com ID " + id + " não existe");
        }
        repository.deleteById(id);
    }
}