package br.ufrn.imd.controle;

import br.ufrn.imd.dao.ItemDAO;
import br.ufrn.imd.dao.SalaDAO;
import br.ufrn.imd.dao.SetorDAO;
import br.ufrn.imd.modelo.Item;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemController {

    private SetorDAO setorDAO = new SetorDAO();
    private SalaDAO salaDAO = new SalaDAO();
    private ItemDAO itemDAO = new ItemDAO();

    @FXML
    private ComboBox<String> comboTipoItem;

    @FXML
    private TextField textNomeNovoItem;

    @FXML
    private TextField textDescricaoNovoItem;

    @FXML
    private ComboBox<String> comboSetorNovoItem;

    @FXML
    private ComboBox<String> comboSalaNovoItem;

    @FXML
    public void initialize() {
        comboTipoItem.setItems(FXCollections.observableArrayList("Eletrônico", "Mobiliário", "Outro"));
        comboSetorNovoItem.setItems(FXCollections.observableArrayList(setorDAO.buscarTodosNomes()));
        comboSalaNovoItem.setItems(FXCollections.observableArrayList(salaDAO.buscarTodosNomes()));
    }

    @FXML
    private void adicionarItem(ActionEvent event) {
        String nome = textNomeNovoItem.getText();
        String descricao = textDescricaoNovoItem.getText();
        String setor = comboSetorNovoItem.getValue();
        String sala = comboSalaNovoItem.getValue();
        String tipo = comboTipoItem.getValue();

        if (nome.isBlank() || descricao.isBlank() || setor == null || sala == null) {
            mostrarAlerta("Erro", "Por favor, preencha todos os campos para adicionar um item.");
            return;
        }

        Item novoItem = new Item("0", nome, descricao, "0", tipo, sala);
        itemDAO.inserirItem(novoItem);
        limparCamposAdicionar();
        mostrarAlerta("Sucesso", "Item adicionado com sucesso.");
    }

    private void limparCamposAdicionar() {
        textNomeNovoItem.clear();
        textDescricaoNovoItem.clear();
        comboSetorNovoItem.getSelectionModel().clearSelection();
        comboSalaNovoItem.getSelectionModel().clearSelection();
    }

    @FXML
    private void voltarAoMenuPrincipal(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/ufrn/imd/visao/MainMenu.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) textNomeNovoItem.getScene().getWindow();
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
}
