package br.ufrn.imd.modelo;

public class Professor extends Pessoa {
    private String materia;
    private String titulo;

    public Professor(int id, String nome, String materia, String titulo) {
        super(id, nome);
        this.titulo = titulo;
        this.materia = materia;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}