package br.ufrn.imd.visao;

public class ItemView {
    private String id;
    private String nome;
    private String descricao;
    private String setor;
    private String tipo;
    private String sala;

    // Construtor
    public ItemView(int id, String nome, String descricao, int sala, String tipo) {
        this.id = String.valueOf(id);
        this.nome = nome;
        this.descricao = descricao;
        //this.setor = setor;
        this.sala = String.valueOf(sala);
        this.tipo = tipo;
    }

    // Getters e Setters
    public String getId() { return id; }
    public String getIdStr() {
        String idStr = String.format(this.id);
        return idStr; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getSala() { return sala; }
    public void setSala(String sala) { this.sala = sala; }


}
