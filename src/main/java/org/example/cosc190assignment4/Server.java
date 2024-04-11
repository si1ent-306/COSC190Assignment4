package org.example.cosc190assignment4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int clientNo = 0;
    public static void main(String []args) {
        startServer();
            // create the server socket to connect
            // requires a port at which the server will serv
    }

    private static void startServer(){
        try (ServerSocket serverSocket = new ServerSocket(9000)){
            System.out.println("Server started. Serving at port: " + 9000);
            while (true) {
                // wait for clients to connect
                System.out.println("Waiting for client to connect");

                //Client 1
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: "
                        +
                        clientNo++
                        + clientSocket.getLocalAddress()
                        + "/"
                        + clientSocket.getPort());
                new DataOutputStream(
                        clientSocket.getOutputStream()).writeInt(clientNo);
                Client client = reciveClientObject(clientSocket);

                Thread connectedThread = new Thread(() -> handleClientConnection(clientSocket, client));
                connectedThread.setDaemon(true);
                connectedThread.start();
                //Client 2
//                createFile();

                // receive the request from the connected client
                // send the response to the connected client

            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Client reciveClientObject(Socket connectedClient) throws IOException, ClassNotFoundException {
        ObjectInputStream on = new ObjectInputStream(connectedClient.getInputStream());
        Client clientObject = (Client) on.readObject();
        System.out.println(clientObject);
        return clientObject;
        //textArea.appendText(message);
    }

    private static void createFile() {
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
        }
    private static void handleClientConnection(Socket clientSocket, Client clientObject) {
        try (DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream())) {
            while (true) {

                String messageRecived = inputStream.readUTF();
                System.out.println(clientObject.Handle + ": " + messageRecived);


                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
                outputStream.writeUTF(messageRecived);
                outputStream.flush();
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
