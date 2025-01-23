package br.ufrn.imd.controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenuController {



    private Stage primaryStage; // Referência ao Stage principal

    // Método para definir o Stage principal
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    // Método genérico para trocar a cena
    private void trocarCena(String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene novaCena = new Scene(root);

            // Atualiza o título e a cena no Stage principal
            primaryStage.setTitle(titulo);
            primaryStage.setScene(novaCena);
        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela: " + fxmlPath);
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirAdicionarExcluir(ActionEvent event) {
        trocarCena("/br/ufrn/imd/visao/AddItem.fxml", "Adicionar Itens");
    }

    @FXML
    private void abrirBuscar(ActionEvent event) {
        trocarCena("/br/ufrn/imd/visao/BuscaView.fxml", "Buscar Itens");
    }

    @FXML
    private void abrirMovimentar(ActionEvent event) {
        trocarCena("/br/ufrn/imd/visao/MovimentacaoView.fxml", "Movimentar Itens");
    }
}
