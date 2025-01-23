package br.ufrn.imd.dao;

import br.ufrn.imd.modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.ufrn.imd.modelo.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;




public class ItemDAO {
    private final Connection conexao;

    public ItemDAO() {
        this.conexao = DatabaseConnection.getConnection();
    }

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS Item (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT NOT NULL," +
                " descricao TEXT," +
                " tipo TEXT," +
                " salaId INTEGER," +
                " salaNome TEXT," +
                " FOREIGN KEY(salaId) REFERENCES Sala(id));";
        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela Item: " + e.getMessage());
        }
    }



    public void inserirItem(Item novoItem) {
        int salaId = 0;
        String sqlSalaId = "SELECT id FROM sala WHERE nome = ?";
        try (PreparedStatement pstmtSalaId = conexao.prepareStatement(sqlSalaId)){
            // Busca o ID do Setor
            pstmtSalaId.setString(1, novoItem.getSalaNome());
            ResultSet rsSalaId = pstmtSalaId.executeQuery();
            if (!rsSalaId.next()) {
                throw new SQLException("ID não encontrado: " + novoItem.getSalaNome());
            }
            salaId = rsSalaId.getInt("id");
        } catch (SQLException e){
            System.err.println("Erro ao buscar sala" + e.getMessage());
        }
        String sql = "INSERT INTO Item (nome, descricao, salaId, tipo, salaNome) VALUES ( ?, ?, ?, ?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, novoItem.getNome());
            pstmt.setString(2, novoItem.getDescricao());
            pstmt.setInt(3, salaId);
            pstmt.setString(4, novoItem.getTipo());
            pstmt.setString(5, novoItem.getSalaNome());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir item com ID: " + novoItem.getId() + " : " + e.getMessage());
        }
    }

    public void removerItem(String id){
        String sql = "DELETE FROM item WHERE id = ?";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void moverItem(String id, String salaId){
            String sql = "UPDATE item SET salaId = ? WHERE id = ?";
            System.out.println(salaId);

            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                pstmt.setString(1, salaId);
                pstmt.setString(2, id);

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    public void listarItens() {
        String sql = "SELECT * FROM Item;";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("id") + ", Nome: " + rs.getString("nome") + ", Sala ID: " + rs.getString("salaId"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar itens: " + e.getMessage());
        }
    }

    public ObservableList<Item> buscarTodosItens() {
        ObservableList<Item> itens = FXCollections.observableArrayList();
        String sql = """
        SELECT i.id, i.nome, i.descricao, i.salaId, i.tipo, i.salaNome
        FROM item i
        JOIN sala s ON i.salaId = s.id
    """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                itens.add(
                        new Item(
                                rs.getString("id"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getString("salaId"),
                                rs.getString("tipo"),
                                rs.getString("salaNome") // Nome da sala
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }


    public ObservableList<Item> buscarPorSetor(String nomeSetor) {
        ObservableList<Item> itens = FXCollections.observableArrayList();
        String sql = """
        SELECT i.id, i.nome, i.descricao, i.salaId, i.tipo, i.salaNome
        FROM item i
        JOIN sala s ON i.salaId = s.id
        JOIN setor t ON s.setorId = t.id
        WHERE t.nome = ?
    """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeSetor);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                itens.add(
                        new Item(
                                rs.getString("id"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getString("salaId"),
                                rs.getString("tipo"),
                                rs.getString("salaNome") // Novo atributo para o nome do setor
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }


    public ObservableList<Item> buscarPorSala(String nomeSala) {
        ObservableList<Item> itens = FXCollections.observableArrayList();
        String sql = """
        SELECT item.id, item.nome, item.descricao, item.salaId, item.tipo, item.salaNome
        FROM item
        WHERE item.salaNome = ?
    """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeSala);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                itens.add(
                        new Item(
                                rs.getString("id"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getString("salaId"),
                                rs.getString("tipo"),
                                rs.getString("salaNome") // Novo atributo para o nome da sala
                        )
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar por sala: " + e.getMessage());
        }
        return itens;
    }

    public ObservableList<Item> buscarPorTipo(String tipo) {
        ObservableList<Item> itens = FXCollections.observableArrayList();
        String sql =  """
        SELECT item.id, item.nome, item.descricao, item.salaId, item.tipo, item.salaNome
        FROM item
        WHERE item.tipo = ?
    """;
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                itens.add(
                        new Item(
                                rs.getString("id"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getString("salaId"),
                                rs.getString("tipo"),
                                rs.getString("salaNome") // Nome da sala
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }

    public ObservableList<Item> buscarPorNome(String nomeItem) {
        ObservableList<Item> itens = FXCollections.observableArrayList();
        String sql = """
        SELECT i.id, i.nome, i.descricao, i.salaId, i.tipo, i.salaNome
        FROM item i
        WHERE i.nome LIKE ? OR i.nome LIKE ? OR i.nome LIKE ? OR i.nome LIKE ?
    """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            // Adiciona os padrões de busca para o nome do item
            stmt.setString(1, nomeItem);         // Palavra sozinha
            stmt.setString(2, nomeItem + " %"); // Palavra no início
            stmt.setString(3, "% " + nomeItem + " %"); // Palavra no meio
            stmt.setString(4, "% " + nomeItem); // Palavra no fim

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                itens.add(
                        new Item(
                                rs.getString("id"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getString("salaId"),
                                rs.getString("tipo"),
                                rs.getString("salaNome") // Nome da sala
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }
}


