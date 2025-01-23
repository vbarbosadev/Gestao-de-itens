package br.ufrn.imd.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.modelo.Item;
import br.ufrn.imd.modelo.Sala;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class SalaDAO {
    private final Connection conexao;
    private static ArrayList<Sala> salas = new ArrayList<Sala>();

    public SalaDAO() {
        this.conexao = DatabaseConnection.getConnection();
    }

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS Sala (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, setorId INTEGER, responsavel TEXT, tipo TEXT, FOREIGN KEY(setorId) REFERENCES Setor(id));";
        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela Sala: " + e.getMessage());
        }
    }

    public void inserirSala(String nome, int setorId, String responsavel, String tipo) {
        String sql = "INSERT INTO Sala (nome, setorId, responsavel, tipo) VALUES (?, ?, ?, ?);";
        Sala sala = new Sala(nome, setorId, responsavel, tipo);
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setInt(2, setorId);
            pstmt.setString(3, responsavel);
            pstmt.setString(4, tipo);
            pstmt.executeUpdate();
            salas.add(sala);
        } catch (SQLException e) {
            System.err.println("Erro ao inserir sala: " + e.getMessage());
        }
    }

    public List<Sala> buscarTodas() {
        List<Sala> salas = new ArrayList<>();
        String sql = """
        SELECT nome, setorId, responsavel, tipo, id
        FROM sala
    """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Sala novaSala = new Sala(
                        rs.getString("nome"),
                        rs.getInt("setorId"),
                        rs.getString("responsavel"),
                        rs.getString("tipo")
                );
                novaSala.setId(rs.getInt("id"));
                salas.add(novaSala);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salas;
    }

    public void listarSalas() {
        String sql = "SELECT * FROM Sala;";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome") + ", Responsável: " + rs.getString("responsavel") + ", Tipo: " + rs.getString("tipo"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar salas: " + e.getMessage());
        }
    }

    public List<String> buscarTodosNomes() {
        List<String> salas = new ArrayList<>();
        String sql = "SELECT nome FROM sala";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                salas.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salas;
    }

    public static ArrayList<Sala> getSalas() {
        return salas;
    }

    public void setSalas(ArrayList<Sala> salas) {
        SalaDAO.salas = salas;
    }

    public int getSalaId(String nome) {
        int id = 0;
        for (Sala s : salas) {
            id = s.getIdByName(nome);
        }
        return id;
    }
}
