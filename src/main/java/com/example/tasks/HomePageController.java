package com.example.tasks;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class HomePageController {

    @FXML
    private Button openNotesPageButton;

    @FXML
    private void openNotesPage() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("notes.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) openNotesPageButton.getScene().getWindow();
            stage.setScene(scene);
    }



}
