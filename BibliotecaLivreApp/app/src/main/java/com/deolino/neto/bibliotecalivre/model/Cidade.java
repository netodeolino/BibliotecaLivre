package com.deolino.neto.bibliotecalivre.model;

import java.util.ArrayList;

/**
 * Created by neto on 14/01/17.
 */

public class Cidade {
    private int codigo;
    private String nome;
    private String estado;

    public Cidade () {}

    public Cidade (int codigo, String nome, String estado) {
        this.codigo = codigo;
        this.nome = nome;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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
}
