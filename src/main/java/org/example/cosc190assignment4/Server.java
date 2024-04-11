package org.example.cosc190assignment4;

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
                Socket connectedClient2 = serverSocket.accept();
                System.out.println("Client 1 connected: "
                        + connectedClient1.getLocalAddress()
                        + "/"
                        + connectedClient1.getPort());
                System.out.println("Client 2 connected: "
                        + connectedClient2.getLocalAddress()
                        + "/"
                        + connectedClient2.getPort());

                createFile();

                // receive the request from the connected client
                DataInputStream inputStreamFromClient =
                        new DataInputStream(connectedClient1.getInputStream());
                String messageFromClient = inputStreamFromClient.readUTF();
                FileWriter fileWriter = new FileWriter("data_file/info.json");

                // send the response to the connected client
                DataOutputStream outputStreamToClient =
                        new DataOutputStream(connectedClient1.getOutputStream());
                String messageToClient = "Hello" + new Date();
                System.out.println("Server responds: " + messageToClient);
                outputStreamToClient.writeUTF(messageToClient);
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

}
