package com.deolino.neto.bibliotecalivre.model;

import java.util.List;

/**
 * Created by neto on 16/01/17.
 */

public class Biblioteca {
    private String nome;
    private String endereco;

    public Biblioteca () {}

    public Biblioteca (String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

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
}
