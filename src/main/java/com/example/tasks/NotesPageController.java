package com.example.tasks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.security.spec.ECField;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class NotesPageController implements Initializable {

    private LinkedList<String> notiztenTitle = new LinkedList<>();

    private LinkedList<String> notiztenContent = new LinkedList<>();

    @FXML
    private ListView<String> noteListView;

    @FXML
    private TextArea noteContentInputField;

    @FXML
    private TextArea noteContentText;

    @FXML
    private TextField noteTitleInputField;

    @FXML
    private void editNote(Event event){
        String selectedNoteTitle = noteListView.getSelectionModel().getSelectedItem(); // "Klausuren"
        String noteContent = noteContentInputField.getText(); // "Mathe2"
        //  notiztenTitle --> ["A", "B", "Klausuren"]
        // notiztenContent--> ["Vektoren", "DGL", "Info2 \n Messtechnik"]
        if(!noteContent.isEmpty() && selectedNoteTitle != null){
            int index = notiztenTitle.indexOf(selectedNoteTitle); // index = 2
            notiztenContent.set(index, noteContent);
            // notiztenContent--> ["Vektoren", "DGL", "Mathe2"]
            noteContentText.clear();
            noteContentInputField.clear();
        }

    }

    @FXML
    private void addNote(ActionEvent event) {
        String noteTitle = noteTitleInputField.getText(); // noteTitle = Mathe
        String noteContent = noteContentInputField.getText(); // noteContent = Vektoren
        if (!noteTitle.isEmpty() && !noteContent.isEmpty()) {
            notiztenTitle.add(noteTitle); // notiztenTitle --> ["A", "B"] --> neue --> ["A", "B", "Mathe"]
            notiztenContent.add(noteContent); // notiztenContent --> ["write something", "do something"]
                                              // neue notiztenContent --> ["write something", "do something", "Vektoren"]
            noteTitleInputField.clear();
            noteContentInputField.clear();
            updateNoteListView();
        }
    }

    @FXML
    private void loadNoteContent(MouseEvent event) {
        String selectedNoteTitle = noteListView.getSelectionModel().getSelectedItem(); // "Mathe"
        // notiztenTitle --> ["A", "B", "Mathe"]
        // notiztenContent --> ["write something", "do something", "Vektoren"]

        // Liste.indexOf(element) --> indexOf gibt und wo das Element in der Liste steht (in welchem Index)
        int index = notiztenTitle.indexOf(selectedNoteTitle); // index = 2
        noteContentText.setText(notiztenContent.get(index));
        // noteContentText.setText(notiztenContent.get(2));
        // noteContentText.setText("Vektoren");
    }

    @FXML
    protected void backToMain(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) noteContentText.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void deleteNote(){
        String selectedNoteTitle = noteListView.getSelectionModel().getSelectedItem(); // "Mathe"
        int index = notiztenTitle.indexOf(selectedNoteTitle); // index = 2
        // entfernung
        notiztenTitle.remove(index);         // notiztenTitle --> ["A", "B", "Mathe"]
        // notiztenTitle.remove(2);  --> notiztenTitle --> ["A", "B"]
        notiztenContent.remove(index);    // notiztenContent --> ["write something", "do something", "Vektoren"]
        // notiztenContent.remove(2); --> notiztenContent --> ["write something", "do something"]
        noteContentText.clear();
        updateNoteListView();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadNotes();
    }

    private void loadNotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("notes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // line = A:write something // diese Zeile enthält der Title und der Inhalt von einer Notiz
                // line = B:do something
                // aber dazwischen steht ein ":"
                    String[] note = line.split(":"); // note ["A", "write something"]
                    // note --> ["B", "do something"]
                        notiztenTitle.add(note[0]); // --> ["A", "B"]
                        notiztenContent.add(note[1]); // --> ["write something", "do something"]
            }
            updateNoteListView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveNotes() {
        // notiztenTitle --> ["A", "B"]
        // notiztenContent --> ["write something", "do something"]
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("notes.txt"))) {
            for (int i = 0; i < notiztenTitle.size(); i++) {
                printWriter.println(notiztenTitle.get(i) + ":" + notiztenContent.get(i));
                // printWriter.println(notiztenTitle.get(i) + ":" + notiztenContent.get(i));
                // printWriter.println(notiztenTitle.get(0) + ":" + notiztenContent.get(0));
                // printWriter.println("A" + ":" + notiztenContent.get(0));
                // printWriter.println("A" + ":" + "write something");


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateNoteListView() {
        ObservableList<String> titles = FXCollections.observableArrayList(notiztenTitle);
        // titles  --> hat alle Werte die in notiztenTitle stehen
        // notiztenTitle --> ["A", "B"]
        // titles --> ["A", "B"]
        noteListView.setItems(titles);
        saveNotes();
    }
}
