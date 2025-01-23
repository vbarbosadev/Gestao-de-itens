package br.ufrn.imd.controle;

import br.ufrn.imd.dao.ItemDAO;
import br.ufrn.imd.dao.SalaDAO;
import br.ufrn.imd.dao.SetorDAO;
import br.ufrn.imd.modelo.Item;
import br.ufrn.imd.visao.ItemView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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

        Item novoItem = new Item(nome, descricao, sala, tipo);
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
    private void buscarPorTipoItem(ActionEvent event) {
        String tipoItem = comboTipoItem.getValue();
        if (tipoItem != null) {
            ObservableList<ItemView> itens = itemDAO.buscarPorTipo(tipoItem);

        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
