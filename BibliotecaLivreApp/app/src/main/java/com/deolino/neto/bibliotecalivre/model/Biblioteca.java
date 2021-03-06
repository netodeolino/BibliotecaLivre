package com.deolino.neto.bibliotecalivre.model;

/**
 * Created by neto on 16/01/17.
 */

public class Biblioteca {
    private String nome;
    private String endereco;
    private int cidade;
    private int codigo;

    public Biblioteca() {}

    public Biblioteca(String nome, String endereco, int cidade) {
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
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

    public int getCidade() {
        return cidade;
    }

    public void setCidade(int cidade) {
        this.cidade = cidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
