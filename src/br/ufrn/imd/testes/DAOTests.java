package br.ufrn.imd.testes;

import br.ufrn.imd.dao.*;
import br.ufrn.imd.modelo.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DAOTests {
    private Connection conexao;
    private ItemDAO itemDAO;
    private SalaDAO salaDAO;
    private PessoaDAO pessoaDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        String URL = "jdbc:sqlite:C:\\Users\\Suporte\\Documents\\GitHub\\vinicius\\lp2\\trabFinal\\src\\database\\testes.db";
        conexao = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Suporte\\Documents\\GitHub\\vinicius\\lp2\\trabFinal\\src\\database\\testes.db");
        DatabaseConnection.setURL(URL);
        itemDAO = new ItemDAO();
        salaDAO = new SalaDAO();
        pessoaDAO = new PessoaDAO();

        itemDAO.criarTabela();
        salaDAO.criarTabela();
        pessoaDAO.criarTabela();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (conexao != null) {
            conexao.close();
        }
    }

    @Test
    public void testInserirSala() {
        salaDAO.inserirSala("Sala 101", 1, "João Silva", "Laboratório");

        List<Sala> salas = salaDAO.buscarTodas();
        assertEquals("Sala 101", salas.get(0).getNome());
    }

    @Test
    public void testInserirItem() {
        salaDAO.inserirSala("Sala 101", 1, "João Silva", "Laboratório");

        Item item = new Item("1", "Microscópio", "Óptico", "1", "Equipamento", "Sala 101");
        itemDAO.inserirItem(item);

        List<Item> itens = itemDAO.buscarTodosItens();
        assertEquals(1, itens.size());
        assertEquals("Microscópio", itens.get(0).getNome());
    }

    @Test
    public void testRemoverItem() {
        salaDAO.inserirSala("Sala 101", 1, "João Silva", "Laboratório");

        Item item = new Item("1", "Microscópio", "Óptico", "1", "Equipamento", "Sala 101");
        itemDAO.inserirItem(item);
        List<Item> itensAntes = itemDAO.buscarTodosItens();
        itemDAO.removerItem("1");
        List<Item> itensDepois = itemDAO.buscarTodosItens();
        assertNotEquals(itensAntes, itensDepois);
    }

    @Test
    public void testMoverItem() {
        salaDAO.inserirSala("Sala 101", 1, "João Silva", "Laboratório");
        salaDAO.inserirSala("Sala 102", 1, "Maria Souza", "Auditório");

        Item item = new Item("1", "Projetor", "Digital", "1", "Equipamento", "Sala 101");
        itemDAO.inserirItem(item);
        itemDAO.moverItem("1", "2");

        List<Item> itens = itemDAO.buscarPorSala("Sala 102");
        assertEquals(1, itens.size());
        assertEquals("Projetor", itens.get(0).getNome());
    }

    @Test
    public void testInserirPessoa() {
        pessoaDAO.inserirPessoa(1, "Carlos Alberto", "Professor");

        List<String> tecnicos = pessoaDAO.buscarTecnicos();
        assertTrue(tecnicos.contains("Carlos Alberto"));
    }

    @Test
    public void testBuscarItensPorTipo() {
        salaDAO.inserirSala("Sala 101", 1, "João Silva", "Laboratório");

        Item item1 = new Item("1", "Microscópio", "Óptico", "1", "Equipamento", "Sala 101");
        Item item2 = new Item("2", "Notebook", "Eletrônico", "1", "Equipamento", "Sala 101");
        itemDAO.inserirItem(item1);
        itemDAO.inserirItem(item2);

        List<Item> itens = itemDAO.buscarPorTipo("Equipamento");
        assertFalse(itens.isEmpty());
    }
}
