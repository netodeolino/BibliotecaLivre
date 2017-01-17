package com.deolino.neto.bibliotecalivre.model;

import java.util.List;

/**
 * Created by neto on 16/01/17.
 */

public class Biblioteca {
    private String nome;
    private String endereco;
    private Cidade cidade;
    private List<Livro> livros;

    public Biblioteca () {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
