package com.example.chatapplication.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientConto implements Initializable {
    @FXML
    private TextField txtMessage;

    @FXML
    private TextArea txtArea;

    private Socket socket;
    private DataInputStream dataInputStream;
    private String message = "";
    private DataOutputStream dataOutputStream;

    @FXML
    void sentMessageToServer(ActionEvent event) throws IOException {
        dataOutputStream = new DataOutputStream( socket.getOutputStream());
        dataOutputStream.writeUTF(txtMessage.getText());
        dataOutputStream.flush();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            try {
                socket = new Socket("localhost",4000);
                txtArea.appendText("Connected to the server \n");

                dataInputStream = new DataInputStream(socket.getInputStream());
                while (!message.equals("exit")){
                    message=dataInputStream.readUTF();
                    txtArea.appendText(message+"\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}
