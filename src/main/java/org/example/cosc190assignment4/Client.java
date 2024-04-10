package org.example.cosc190assignment4;

public class Client {
    String Username;
    String Handle;
    String Email;

    public Client(String username, String handle, String email) {
        Username = username;
        Handle = handle;
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getHandle() {
        return Handle;
    }

    public void setHandle(String handle) {
        Handle = handle;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "Username='" + Username + '\'' +
                ", Handle='" + Handle + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
