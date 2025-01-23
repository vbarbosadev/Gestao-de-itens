package br.ufrn.imd.dao;

import br.ufrn.imd.modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.ufrn.imd.visao.ItemView;
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
        String sql = "INSERT INTO Item (nome, descricao, salaId, tipo) VALUES ( ?, ?, ?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, novoItem.getNome());
            pstmt.setString(2, novoItem.getDescricao());
            pstmt.setInt(3, salaId);
            pstmt.setString(4, novoItem.getTipo());

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

    public void listarItens() {
        String sql = "SELECT * FROM Item;";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome") + ", Sala ID: " + rs.getInt("salaId"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar itens: " + e.getMessage());
        }
    }

    public ObservableList<ItemView> buscarTodosItens(){
        ObservableList<ItemView> itens = FXCollections.observableArrayList();
        String sql = "SELECT * FROM item";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                itens.add(
                        new ItemView(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getInt("salaId"),
                                rs.getString("tipo")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }

    public ObservableList<ItemView> buscarPorSetor(String nomeSetor) {
        ObservableList<ItemView> itens = FXCollections.observableArrayList();
        String sql = "SELECT i.* FROM item i " +
                "JOIN sala s ON i.salaId = s.id " +
                "JOIN setor t ON s.setorId = t.id " +
                "WHERE t.nome = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeSetor);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                itens.add(
                        new ItemView(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("salaId"),
                        rs.getString("tipo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }

    public ObservableList<ItemView> buscarPorSala(String nomeSala) {
        //List<ItemView> itens = new ArrayList<>();
        ObservableList<ItemView> itens = FXCollections.observableArrayList();
        String sql = "SELECT * FROM item WHERE salaId = (SELECT id FROM sala WHERE nome = ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeSala);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                 itens.add(
                        new ItemView(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("salaId"),
                        rs.getString("tipo")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar por sala: " + e.getMessage()) ;
        }
        return itens;
    }

    public ObservableList<ItemView> buscarPorTipo(String tipo) {
        ObservableList<ItemView> itens = FXCollections.observableArrayList();
        String sql = "SELECT * FROM item WHERE tipo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                itens.add(
                        new ItemView(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("salaId"),
                        rs.getString("tipo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }

    public ObservableList<ItemView> buscarPorNome(String nomeItem) {
        ObservableList<ItemView> item = FXCollections.observableArrayList();
        String sql = "SELECT * FROM item WHERE nome = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeItem);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                item.add(
                        new ItemView(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("salaId"),
                        rs.getString("tipo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
    }


