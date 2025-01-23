package br.ufrn.imd.visao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import br.ufrn.imd.controle.MainMenuController;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/ufrn/imd/visao/MainMenu.fxml"));
            Parent root = loader.load();

            // Obtém o controlador e passa o Stage principal
            MainMenuController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);

            primaryStage.setTitle("Menu Principal");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Erro ao inicializar o aplicativo:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
