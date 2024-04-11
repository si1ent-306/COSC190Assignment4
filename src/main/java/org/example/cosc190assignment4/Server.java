package org.example.cosc190assignment4;

import javafx.application.Platform;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
    public static void main(String []args) {

        // create the server socket to connect
        // requires a port at which the server will serve
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Server started. Serving at port: " + 9000);

            while (true) {
                // wait for clients to connect
                System.out.println("Waiting for client to connect");

                Socket connectedClient1 = serverSocket.accept();
                System.out.println("Client 1 connected: "
                        + connectedClient1.getLocalAddress()
                        + "/"
                        + connectedClient1.getPort());
                createFile();

                // receive the request from the connected client
                ObjectInputStream on = new ObjectInputStream(connectedClient1.getInputStream());
                FileWriter fileWriter = new FileWriter("data_file/info.json");
                fileWriter.write(on.readUTF());
                handleClientConnection(connectedClient1);

                // send the response to the connected client

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void createFile() throws IOException {

            File fileObj = new File("data_files/info.json");

            if( ! fileObj.exists() ){
                if( ! new File("data_files/info.json").createNewFile() ){
                    throw new IOException("File creation failed");
                }
            } else {
                throw new IOException("File already exists");
            }

        }
    private static void handleClientConnection(Socket clientSocket) {
        try (DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream())) {
            while (true) {
                DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                String messageRecived = inputStream.readUTF();
                String messageToSend = inputStream.readUTF();
                dataOutputStream.writeUTF(messageToSend);
                dataOutputStream.flush();

                Platform.runLater(() -> {
//                    showMessage("Received message: " + messageRecived + "\n");
//                    showMessage("Sending message: " + messageToSend + "\n");
                });


            }
        } catch (IOException e) {
//            Platform.runLater(() -> showMessage("Client disconnected: "
//                    + clientSocket.getInetAddress()
//                    + ", Port" + clientSocket.getPort()
//                    + "\n"));
        }
    }
    private void showMessage(String message) {
//        textArea.appendText(message);
    }


}
