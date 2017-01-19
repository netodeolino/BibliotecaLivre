package com.deolino.neto.bibliotecalivre.model;

import java.util.ArrayList;

/**
 * Created by neto on 14/01/17.
 */

public class Cidade {
    private int codigo;
    private String nome;
    private String siglaEstado;

    public Cidade () {}

    public Cidade (int codigo, String nome, String siglaEstado) {
        this.codigo = codigo;
        this.nome = nome;
        this.siglaEstado = siglaEstado;
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

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }
}
