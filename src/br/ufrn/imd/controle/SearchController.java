package br.ufrn.imd.controle;


import br.ufrn.imd.dao.SetorDAO;
import br.ufrn.imd.dao.SalaDAO;
import br.ufrn.imd.dao.ItemDAO;

import java.util.List;

import br.ufrn.imd.modelo.Item;
import br.ufrn.imd.visao.ItemView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SearchController {

    private SetorDAO setorDAO = new SetorDAO();
    private SalaDAO salaDAO = new SalaDAO();
    private ItemDAO itemDAO = new ItemDAO();


    @FXML
    private ComboBox<String> comboSetor;

    @FXML
    private ComboBox<String> comboSala;

    @FXML
    private ComboBox<String> comboTipoItem;

    @FXML
    private TextField textNomeItem;


    @FXML
    private TableView<ItemView> tableResultados;

    @FXML
    private TableColumn<ItemView, String> colunaId;
    @FXML
    private TableColumn<ItemView, String> colunaNome;
    @FXML
    private TableColumn<ItemView, String> colunaDescricao;
    @FXML
    private TableColumn<ItemView, String> colunaSetor;
    @FXML
    private TableColumn<ItemView, String> colunaSala;



    @FXML
    public void initialize() {
        colunaId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        colunaNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colunaDescricao.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricao()));
        colunaSetor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipo()));
        colunaSala.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSala()));

        // Inicializa os ComboBoxes
        comboSetor.setItems(FXCollections.observableArrayList(setorDAO.buscarTodosNomes()));
        comboSala.setItems(FXCollections.observableArrayList(salaDAO.buscarTodosNomes()));
        comboTipoItem.setItems(FXCollections.observableArrayList("Eletrônico", "Mobiliário", "Outro"));
    }

    @FXML
    private void buscarPorSetor(ActionEvent event) {
        String setor = comboSetor.getValue();
        if (setor != null) {

            ObservableList<ItemView> itens = itemDAO.buscarPorSetor(setor);
            atualizarTabela(itens);
        }
    }

    @FXML
    private void buscarPorSala(ActionEvent event) {
        String sala = comboSala.getValue();
        if (sala != null) {
            ObservableList<ItemView> itens = itemDAO.buscarPorSala(sala);
            atualizarTabela(itens);
        }
    }

    @FXML
    private void buscarPorTipoItem(ActionEvent event) {
        String tipoItem = comboTipoItem.getValue();
        if (tipoItem != null) {
            ObservableList<ItemView> itens = itemDAO.buscarPorTipo(tipoItem);
            atualizarTabela(itens);
        }
    }

    @FXML
    private void excluirItemSelecionado(ActionEvent event) {
        ItemView itemSelecionado = tableResultados.getSelectionModel().getSelectedItem();
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
        ObservableList<ItemView> itens = itemDAO.buscarTodosItens();
        atualizarTabela(itens);

    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void buscarItemUnico(ActionEvent event) {
        String nomeItem = textNomeItem.getText();
        if (nomeItem != null && !nomeItem.isBlank()) {
            ObservableList<ItemView> itens = itemDAO.buscarPorNome(nomeItem);
            atualizarTabela(itens);
        }
    }

    private void atualizarTabela(ObservableList<ItemView> data) {
        tableResultados.setItems(data);
    }


}