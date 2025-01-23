package br.ufrn.imd.controle;

import br.ufrn.imd.dao.SetorDAO;
import br.ufrn.imd.dao.SalaDAO;
import br.ufrn.imd.dao.ItemDAO;
import br.ufrn.imd.modelo.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class BuscaController {

    private SetorDAO setorDAO = new SetorDAO();
    private SalaDAO salaDAO = new SalaDAO();
    private ItemDAO itemDAO = new ItemDAO();

    private Stage primaryStage; // Referência ao Stage principal

    @FXML
    private ComboBox<String> comboSetor;

    @FXML
    private ComboBox<String> comboSala;

    @FXML
    private ComboBox<String> comboTipoItem;

    @FXML
    private TextField textNomeItem;

    @FXML
    private TableView<Item> tableResultados;

    @FXML
    private TableColumn<Item, String> colunaId;
    @FXML
    private TableColumn<Item, String> colunaNome;
    @FXML
    private TableColumn<Item, String> colunaDescricao;
    @FXML
    private TableColumn<Item, String> colunaSetor;
    @FXML
    private TableColumn<Item, String> colunaSala;

    @FXML
    public void initialize() {
        colunaId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        colunaNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colunaDescricao.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricao()));
        colunaSetor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipo()));
        colunaSala.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSala()));

        // Inicializa os ComboBoxes
        comboSetor.setItems(FXCollections.observableArrayList(setorDAO.buscarTodosNomes()));
        comboSala.setItems(FXCollections.observableArrayList(salaDAO.buscarTodosNomes()));
        comboTipoItem.setItems(FXCollections.observableArrayList("Eletrônico", "Mobiliário", "Outro"));
    }

    // Método para definir o Stage principal
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void buscarPorSetor(ActionEvent event) {
        String setor = comboSetor.getValue();
        if (setor != null) {
            ObservableList<Item> itens = itemDAO.buscarPorSetor(setor);
            atualizarTabela(itens);
        }
    }

    @FXML
    private void buscarPorSala(ActionEvent event) {
        String sala = comboSala.getValue();
        if (sala != null) {
            ObservableList<Item> itens = itemDAO.buscarPorSala(sala);
            atualizarTabela(itens);
        }
    }

    @FXML
    private void buscarPorTipoItem(ActionEvent event) {
        String tipoItem = comboTipoItem.getValue();
        if (tipoItem != null) {
            ObservableList<Item> itens = itemDAO.buscarPorTipo(tipoItem);
            atualizarTabela(itens);
        }
    }

    @FXML
    private void excluirItemSelecionado(ActionEvent event) {
        Item itemSelecionado = tableResultados.getSelectionModel().getSelectedItem();
        if (itemSelecionado == null) {
            mostrarAlerta("Erro", "Nenhum item selecionado para exclusão.");
            return;
        }

        itemDAO.removerItem(itemSelecionado.getIdStr());
        atualizarTabela(itemDAO.buscarTodosItens());
        mostrarAlerta("Sucesso", "Item excluído com sucesso.");
    }

    @FXML
    private void buscarTodos(ActionEvent event) {
        ObservableList<Item> itens = itemDAO.buscarTodosItens();
        atualizarTabela(itens);
    }

    @FXML
    private void buscarItemUnico(ActionEvent event) {
        String nomeItem = textNomeItem.getText();
        if (nomeItem != null && !nomeItem.isBlank()) {
            ObservableList<Item> itens = itemDAO.buscarPorNome(nomeItem);
            atualizarTabela(itens);
        }
    }

    @FXML
    private void voltarAoMenuPrincipal(ActionEvent event) {
        try {



            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/ufrn/imd/visao/MainMenu.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) textNomeItem.getScene().getWindow();
            stage.setTitle("Menu Principal");
            stage.setScene(new Scene(root));

            MainMenuController controller = loader.getController();
            controller.setPrimaryStage(stage);

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Não foi possível voltar ao menu principal.");
        }


    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void atualizarTabela(ObservableList<Item> data) {
        tableResultados.setItems(data);
    }
}
