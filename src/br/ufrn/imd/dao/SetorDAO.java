package br.ufrn.imd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

public class SetorDAO {
    private final Connection conexao;

    public SetorDAO() {
        this.conexao = DatabaseConnection.getConnection();
    }

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS Setor (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL);";
        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela Setor: " + e.getMessage());
        }
    }

    public void inserirSetor(String nome) {
        String sql = "INSERT INTO Setor (nome) VALUES (?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir setor: " + e.getMessage());
        }
    }

    public void listarSetores() {
        String sql = "SELECT * FROM Setor;";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar setores: " + e.getMessage());
        }
    }


    public List<String> buscarTodosNomes() {
        List<String> setores = new ArrayList<>();
        String sql = "SELECT nome FROM setor";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                setores.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setores;
    }
}




