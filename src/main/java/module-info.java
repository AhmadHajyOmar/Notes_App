module com.example.tasks {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tasks to javafx.fxml;
    exports com.example.tasks;
}