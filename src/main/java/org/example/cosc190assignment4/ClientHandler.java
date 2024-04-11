package org.example.cosc190assignment4;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket clientSocket1;
    private final Socket clientSocket2;
    private DataInputStream fromClient1;
    private DataInputStream fromClient2;
    private DataOutputStream toClient1;
    private DataOutputStream toClient2;

    public ClientHandler(Socket clientSocket1, Socket clientSocket2) {
        this.clientSocket1 = clientSocket1;
        this.clientSocket2 = clientSocket2;
    }

    @Override
    public void run() {
        try {
            DataInputStream fromClient1 = new DataInputStream(clientSocket1.getInputStream());
            DataInputStream fromClient2 = new DataInputStream(clientSocket2.getInputStream());
            DataOutputStream toClient1 = new DataOutputStream(clientSocket1.getOutputStream());
            DataOutputStream toClient2 = new DataOutputStream(clientSocket2.getOutputStream());

            toClient1.writeUTF("Hello Client 1");
            toClient2.writeUTF("Hello Client 2");
            while(true) {
                toClient1.writeUTF(fromClient1.readUTF());
                toClient2.writeUTF(fromClient2.readUTF());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
