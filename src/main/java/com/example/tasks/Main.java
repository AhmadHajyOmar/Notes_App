package com.example.tasks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

// A extends B (vererbung)
public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        // hier werden die Elemente von der fxml Datei : hello-view im Objekt fxmlLoader gespeichert
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        // hier erstellen wir ein neues Scene mit Hilfe von der Elemente im Objekt fxmlLoader
        Scene scene = new Scene(fxmlLoader.load());
        // hier ersetzen wir der Title von Stage
        stage.setTitle("Tasks");
        // hier setzen wir das schon erstellte Scene in der Stage
        stage.setScene(scene);
        // hier wird die gesamte Stage mit der Scene gezeigt
        stage.show();
    }


    public static void main(String[] args) {
        // eine Methode von der Klasse Application
        launch();
        // Die launch Methode ruft die Methode start auf
    }



}