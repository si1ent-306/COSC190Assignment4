package org.example.cosc190assignment4;

import javafx.scene.paint.Color;

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
        while (true) {
            sendClientMessage(socket, scanner.nextLine());
            receiveClientData(socket);
        }
    }
    private static Client getClientData() throws IOException {

        File info = new File("data_files/info.json");

        if (!info.exists())
            info.createNewFile();

        Scanner inputLine = new Scanner(System.in);
        BufferedReader fRead = new BufferedReader(new FileReader(info));

        Client client = new Client();

        //if data doesn't exist or is in an invalid start, get new data
        if (fRead.readLine() == null) {
            //close the reader in case it corrupts the data
            System.out.println(fRead.read());
            fRead.close();
            DataOutputStream toFile = new DataOutputStream(new FileOutputStream(info));
            // input name
            System.out.print("Enter username name: ");
            client.setUsername(inputLine.next());

            // input email
            System.out.print("Enter email: ");
            client.setEmail(inputLine.next());

            // input handle
            System.out.print("Enter handle: ");
            client.setHandle(inputLine.next());

            //get colour once the GUI is ready
            //(If we're implementing it at all)
            //for now, just set it to black
            //client.setColor(Color.BLACK);
            //toFile.write(client.toString().getBytes());

        /* Prompt user to choose preferred text color*/
//            System.out.println("Choose your preferred text color:");
//            System.out.println("1. Red");
//            System.out.println("2. Green");
//            System.out.println("3. Blue");
//            System.out.print("Enter your choice (1/2/3): ");
//            client.setColor(Color.valueOf(inputLine.next()));
//            String textColor;
//            switch (client.Colour) {
//                case "1":
//                    textColor = "Red";
//                    break;
//                case "2":
//                    textColor = "Green";
//                    break;
//                case "3":
//                    textColor = "Blue";
//                    break;
//                default:
//                    textColor = "Black"; // Default to black color
//                    break;ew

//
}
        //else, file already exists, so read it and send it off.
        else {
            fRead = new BufferedReader(new FileReader(info));
            String clientDatum = fRead.readLine();
            //String[] data = clientDatum.split("(?<=\\\")(.[^: ]*?)(?=\\\")");
            String[] data = clientDatum.split("\\{\"|\": \"|\", \"|\"}");
            //Should get {"Username", username, "Handle", handle, "Email", email, "Color", color}
            //Mostly does, but for some reason data[0] is just a space and the rest is shifted one index right.
            System.out.println("Welcome back, " + data[2]);
            client.setUsername(data[2]);
            client.setHandle(data[4]);
            client.setEmail(data[6]);
            //client.setColor(Color.BLACK);
        }
        return client;
    }
    private static void sendClientData(Socket socket) {
        ObjectOutputStream outputStreamToServer;
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
        System.out.println("Message sent: " + message);
    }
    private static void receiveClientData(Socket socket) throws IOException {
        DataInputStream inputStreamFromServer =
                new DataInputStream(socket.getInputStream());
        String messageFromServer = inputStreamFromServer.readUTF();
        System.out.println("Server says: " + messageFromServer);
    }

}
