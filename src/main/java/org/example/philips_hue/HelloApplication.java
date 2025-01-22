package org.example.philips_hue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 378, 793);

        // Configurar la transparencia
        // Ventana sin bordes ni fondo
        stage.initStyle(StageStyle.TRANSPARENT);

        // Fondo de la escena transparente
        scene.setFill(null);

        stage.setTitle("Alex Garcia Sanchez - APP DOMÓTICA");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
