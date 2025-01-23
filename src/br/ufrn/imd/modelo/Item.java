package br.ufrn.imd.modelo;

import br.ufrn.imd.dao.SalaDAO;

public class Item {
    private int id;
    private String nome;
    private String salaNome;
    private String descricao;
    private String tipo;


    public Item(String nome, String descricao, String salaNome, String tipo) {
        this.nome = nome;
        this.descricao = descricao;
        this.salaNome = salaNome;
        this.tipo = tipo;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSalaNome() {
        return salaNome;
    }

    public void setSalaNome(String salaNome) {
        this.salaNome = salaNome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
