package com.deolino.neto.bibliotecalivre.model;

/**
 * Created by neto on 10/01/17.
 */

public class Livro {
    private String nome;
    private int ano;
    private String ISBN;
    private String autor;
    private String categoria;

    public Livro () {}

    public Livro (String nome, int ano, String ISBN, String autor, String categoria) {
        this.nome = nome;
        this.ano = ano;
        this.ISBN = ISBN;
        this.autor = autor;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
