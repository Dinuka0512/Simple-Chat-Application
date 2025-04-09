module com.example.chatapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chatapplication.controller to javafx.fxml;
    exports com.example.chatapplication;
}