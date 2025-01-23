
package br.ufrn.imd.dao;

import br.ufrn.imd.modelo.Pessoa;
import br.ufrn.imd.modelo.Tecnico;
import br.ufrn.imd.modelo.Professor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    private final Connection conexao;

    public PessoaDAO() {
        this.conexao = DatabaseConnection.getConnection();
    }

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS Pessoa (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, tipo TEXT NOT NULL);";
        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela Pessoa: " + e.getMessage());
        }
    }

    public void inserirPessoa(int id, String nome, String tipo) {
        String sql = "INSERT INTO Pessoa (id, nome, tipo) VALUES (?, ?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, nome);
            pstmt.setString(3, tipo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir pessoa: " + e.getMessage());
        }
    }

    public void listarPessoas() {
        String sql = "SELECT * FROM Pessoa;";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome") + ", Tipo: " + rs.getString("tipo"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar pessoas: " + e.getMessage());
        }
    }

    public List<String> buscarTecnicos() {
        List<String> tecnicos = new ArrayList<>();
        String sql = "SELECT nome FROM Pessoa";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tecnicos.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tecnicos;

    }
}
