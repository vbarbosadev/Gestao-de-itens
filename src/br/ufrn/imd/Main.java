package br.ufrn.imd;

import br.ufrn.imd.dao.*;
import br.ufrn.imd.modelo.Item;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.initializeDatabase();

        PessoaDAO pessoaDAO = new PessoaDAO();
        SetorDAO setorDAO = new SetorDAO();
        SalaDAO salaDAO = new SalaDAO();
        ItemDAO itemDAO = new ItemDAO();

        // Criação de setores
        setorDAO.inserirSetor("Setor Administrativo");
        setorDAO.inserirSetor("Setor Acadêmico");
        setorDAO.inserirSetor("Setor de Pesquisa");
        setorDAO.listarSetores();

        // Criação de salas
        salaDAO.inserirSala("Sala 101", 1, "Maria Silva", "Sala de Aula");
        salaDAO.inserirSala("Sala 102", 1, "João Souza", "Sala de Reunião");
        salaDAO.inserirSala("Sala 201", 2, "Pedro Santos", "Sala de Aula");
        salaDAO.inserirSala("Sala 202", 2, "Ana Costa", "Sala de Estudo");
        salaDAO.inserirSala("Laboratório 1", 3, "Lucas Almeida", "Laboratório");
        salaDAO.inserirSala("Laboratório 2", 3, "Mariana Rocha", "Laboratório");
        salaDAO.inserirSala("Sala de TI", 1, "Carlos Souza", "Administrativa");
        salaDAO.inserirSala("Biblioteca", 2, "Fernanda Lima", "Espaço de Leitura");
        salaDAO.inserirSala("Auditório", 3, "Juliana Alves", "Sala de Apresentações");
        salaDAO.inserirSala("Sala de Treinamento", 1, "Ricardo Lopes", "Sala de Capacitação");
        salaDAO.listarSalas();

        String[] salas = {"Sala 101", "Sala 102", "Sala 201", "Sala 202", "Laboratório 1", "Laboratório 2", "Sala de TI", "Biblioteca", "Auditório", "Sala de Treinamento"};

        // Criação de itens com nomes variados e distribuição entre as salas
        String[] nomesItens = {"Projetor", "Computador", "Notebook", "Mesa", "Cadeira", "Lousa", "Ar Condicionado", "Armário", "Roteador", "Impressora"};
        for (int i = 1; i <= 60; i++) {
            int j = i-1;
            while(j > 9){
                j = j - 9;
            }
            String salaDestino = salas[j];
            String nomeItem = nomesItens[(i - 1) % nomesItens.length] + " " + i;
            String descricaoItem = "Descrição do " + nomeItem;
            String tipoItem = (i % 2 == 0) ? "Eletrônico" : "Mobiliário";

            Item item = new Item("0", nomeItem, descricaoItem, String.valueOf(i), tipoItem, salaDestino);
            itemDAO.inserirItem(item);
        }
        itemDAO.listarItens();

        // Criação de pessoas
        pessoaDAO.inserirPessoa(1, "Carlos", "Técnico");
        pessoaDAO.inserirPessoa(2, "Ana", "Professora");
        pessoaDAO.inserirPessoa(3, "João", "Pesquisador");
        pessoaDAO.inserirPessoa(4, "Fernanda", "Administradora");
        pessoaDAO.listarPessoas();
    }
}
