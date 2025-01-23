package br.ufrn.imd.modelo;

public class Item {

    private int id;
    private String nome;
    private int salaId;
    private String salaNome;
    private String descricao;
    private String tipo;

    public Item(String id, String nome, String descricao, String salaId, String tipo, String salaNome) {
        this.id = Integer.parseInt(id);
        this.nome = nome;
        this.descricao = descricao;
        this.salaNome = salaNome;
        this.tipo = tipo;
        this.salaId = Integer.parseInt(salaId);
    }

    public int getId() {
        return id;
    }

    public String getIdAsString() {
        return String.valueOf(id);
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

    public int getSalaId() {
        return salaId;
    }

    public void setSalaId(int salaId) {
        this.salaId = salaId;
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

    public String getDescricaoAsString() {
        return descricao != null ? descricao : "";
    }

    public String getTipoAsString() {
        return tipo != null ? tipo : "";
    }

    public String getSala() {
        return this.salaNome;
    }

    public String getSalaIdStr() {
        return String.valueOf(this.salaId);
    }

    public String getIdStr() {
        return String.valueOf(this.id);
    }
}
