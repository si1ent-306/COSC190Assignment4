package org.example.cosc190assignment4;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9000);
        //Send Client object
        sendClientData(socket);
        Scanner scanner = new Scanner(System.in);
        // receive the response from the server
        while (true){
            sendClientMessage(socket, scanner.nextLine());
        }

    }
    private static Client getClientData() {

        Scanner inputLine = new Scanner(System.in);

        Client client = new Client();

        // input  name
        System.out.print("Enter username name: ");
        client.setUsername(inputLine.next());

        // input email
        System.out.print("Enter email: ");
        client.setEmail(inputLine.next());

        // input handle
        System.out.print("Enter handle: ");
        client.setHandle(inputLine.next());

        return client;
    }
    private static void sendClientData(Socket socket) {
        ObjectOutputStream outputStreamToServer = null;
        try {
            outputStreamToServer = new ObjectOutputStream(socket.getOutputStream());
            outputStreamToServer.writeObject(getClientData());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void sendClientMessage(Socket socket, String message) throws IOException {
            DataOutputStream outputStreamToServer = new DataOutputStream(socket.getOutputStream());
            outputStreamToServer.writeUTF(message);
    }
    private static void reiciveClientData(Socket socket) throws IOException {
        DataInputStream inputStreamToServer = new DataInputStream(socket.getInputStream());
        System.out.println((String) inputStreamToServer.readUTF());
    }

}
