package br.ufrn.imd.modelo;

public class Sala {
    private int id;
    private String nome;
    private int setorId;
    private String responsavel;
    private String tipo; // Pode ser "Administrativa" ou "Sala de Aula"

    public Sala(String nome, int setorId, String responsavel, String tipo) {
        this.nome = nome;
        this.setorId = setorId;
        this.responsavel = responsavel;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public int getIdByName(String nome) {
        if(this.nome != nome){
            return 0;
        }
        return this.id;
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

    public int getSetorId() {
        return setorId;
    }

    public void setSetorId(int setorId) {
        this.setorId = setorId;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}