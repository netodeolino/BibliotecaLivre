package com.deolino.neto.bibliotecalivre.model;

/**
 * Created by neto on 14/01/17.
 */

public class Cidade {
    private String nome;
    private String estado;
    private int codigo;

    public Cidade () {}

    public Cidade (String nome, String estado, int codigo) {
        this.nome = nome;
        this.estado = estado;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
