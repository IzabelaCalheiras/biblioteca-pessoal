package com.biblioteca.biblioteca_pessoal.model;

import java.lang.annotation.Inherited;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_livros")
public class Livro {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, length = 100)
    private String autor;

    @Column(length = 20)
    private String isbn;

    private Integer ano;

    @Column(nullable = false)
    private Boolean lido = false;

    // Construtor vazio 
    public Livro() {}

    // Construtor com parãmetros 
    public Livro(String titulo, String autor, String isbn, Integer ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.ano = ano;
        this.lido = false;
    }

    // Getters e Setterrs
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public Boolean getLido() { return lido; }
    public void setLido(Boolean lido) { this.lido = lido; }
}