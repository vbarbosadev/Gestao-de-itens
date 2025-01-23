package br.ufrn.imd.modelo;

public class Tecnico extends Pessoa {
    private String funcao;

    public Tecnico(int id, String nome, String funcao) {
        super(id, nome);
        this.funcao = funcao;
    }


    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}