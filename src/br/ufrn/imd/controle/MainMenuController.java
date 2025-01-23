package br.ufrn.imd.controle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private void abrirAdicionarExcluir(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/ufrn/imd/visao/AddDeleteView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Adicionar e Remover Itens");
            stage.setScene(new Scene(root, 600, 400)); // Defina o tamanho desejado da tela
            stage.show();
        } catch (Exception e) {
            System.err.println("Erro ao abrir a tela de busca:");
            e.printStackTrace();
        }
    }


    @FXML
    public void abrirBuscar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/ufrn/imd/visao/SearchView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Buscar Itens");
            stage.setScene(new Scene(root, 600, 600)); // Defina o tamanho desejado da tela
            stage.show();
        } catch (Exception e) {
            System.err.println("Erro ao abrir a tela de busca:");
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirMovimentar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/ufrn/imd/visao/MovimentacaoView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Movimentar Itens");
            stage.setScene(new Scene(root, 600, 600)); // Defina o tamanho desejado da tela
            stage.show();
        } catch (Exception e) {
            System.err.println("Erro ao abrir a tela de busca:");
            e.printStackTrace();
        }
    }

    private void abrirNovaJanela(String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela: " + fxmlPath);

            e.printStackTrace();
        }
    }

    private void exibirMensagem(String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
