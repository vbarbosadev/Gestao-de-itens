package br.ufrn.imd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe Singleton para gerenciar a conexão com o banco de dados.
 */
public class DatabaseConnection {
    private static Connection conexao;
    private static final String URL = "jdbc:sqlite:C:\\Users\\vinicius\\Documents\\GitHub\\Inventario\\src\\database\\database.db";

    private DatabaseConnection() {
        // Construtor privado para evitar instâncias externas
    }



    /**
     * Retorna uma instância única de Connection.
     *
     * @return Conexão com o banco de dados.
     */
    public static Connection getConnection() {
        if (conexao == null) {
            synchronized (DatabaseConnection.class) {
                if (conexao == null) {
                    try {
                        conexao = DriverManager.getConnection(URL);
                    } catch (SQLException e) {
                        throw new RuntimeException("Erro ao conectar ao banco de dados", e);
                    }
                }
            }
        }
        return conexao;
    }

    /**
     * Inicializa as tabelas no banco de dados chamando os DAOs.
     */
    public static void initializeDatabase() {
        SetorDAO setorDAO = new SetorDAO();
        SalaDAO salaDAO = new SalaDAO();
        ItemDAO itemDAO = new ItemDAO();
        PessoaDAO pessoaDAO = new PessoaDAO();

        setorDAO.criarTabela();
        salaDAO.criarTabela();
        itemDAO.criarTabela();
        pessoaDAO.criarTabela();
    }
}