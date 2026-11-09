package com.biblioteca.biblioteca_pessoal.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /** 400 - Erros de validação Bean Validation */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail tratarValidacao(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Erro de validação");
        problem.setDetail("Um ou mais campos estão inválidos");
        problem.setInstance(URI.create(request.getRequestURI()));

        Map<String, String> campos = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(erro -> 
            campos.put(erro.getField(), erro.getDefaultMessage())
        );
        problem.setProperty("campos", campos);

        return problem;
    }

    /** 404 - Recurso não encontrado */
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ProblemDetail tratarNaoEncontrado(RecursoNaoEncontradoException ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Recurso não encontrado");
        problem.setInstance(URI.create(request.getRequestURI()));
        return problem;
    }

    /** 422 - Regra de negócio violada */
    @ExceptionHandler(RegraNegocioException.class)
    public ProblemDetail tratarRegra(RegraNegocioException ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        problem.setTitle("Regra de negócio violada");
        problem.setInstance(URI.create(request.getRequestURI()));
        return problem;
    }

    /** 500 - Erro genérico */
    @ExceptionHandler(Exception.class)
    public ProblemDetail tratarGenerico(Exception ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setTitle("Erro interno do servidor");
        problem.setDetail("Ocorreu um erro inesperado.");
        problem.setInstance(URI.create(request.getRequestURI()));
        return problem;
    }
}