package com.example.chatapplication.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerContro  implements Initializable {
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String message = "";

    @FXML
    private TextField txtMessage;

    @FXML
    private TextArea txtArea;

    @FXML
    void sentMessageToClient(ActionEvent event) throws IOException {
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(txtMessage.getText());
        dataOutputStream.flush();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(()->{
            try {
                serverSocket = new ServerSocket(4000);
                txtArea.appendText("Server Started...\n");

                //Client Side Request Accept
                // Add message to Local host
                socket = serverSocket.accept();
                txtArea.appendText("Client Connected!\n");


                //Ena massage ekak Read Kara ganna Data Input Stream use kkaranawa
                dataInputStream = new DataInputStream(socket.getInputStream());
                while (!message.equals("exit")){
                    message=dataInputStream.readUTF();
                    txtArea.appendText(message+"\n");
                }
                //message
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}
