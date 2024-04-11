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
//                createFile();

                // receive the request from the connected client
                Client client = reciveClientObject(connectedClient1);
                handleClientConnection(connectedClient1, client);
                // send the response to the connected client

            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Client reciveClientObject(Socket connectedClient1) throws IOException, ClassNotFoundException {
        ObjectInputStream on = new ObjectInputStream(connectedClient1.getInputStream());
        Client clientObject = (Client) on.readObject();
        System.out.println(clientObject);
        return clientObject;
        //textArea.appendText(message);
    }

    //    private static void createFile() throws IOException {
//                FileWriter fileWriter = new FileWriter("data_file/info.json");
//                fileWriter.write(on.readUTF());
//
//            File fileObj = new File("data_files/info.json");
//
//            if( ! fileObj.exists() ){
//                if( ! new File("data_files/info.json").createNewFile() ){
//                    throw new IOException("File creation failed");
//                }
//            } else {
//                throw new IOException("File already exists");
//            }
//
//        }
    private static void handleClientConnection(Socket clientSocket, Client clientObject) {
        try (DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream())) {
            while (true) {
                    DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                    String messageRecived = (String) in.readUTF();
                    System.out.println(clientObject.Handle + ": " + messageRecived);
                }
//                Platform.runLater(() -> {
//                    showMessage("Received message: " + messageRecived + "\n");
//                    showMessage("Sending message: " + messageToSend + "\n");
//                });
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
