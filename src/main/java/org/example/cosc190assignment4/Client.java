package org.example.cosc190assignment4;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class  Client implements Serializable {
    String Username;
    String Handle;
    String Email;
    Color Color;

    public Client(String username, String handle, String email, Color colour) {
        Username = username;
        Handle = handle;
        Email = email;
        //Color = colour;
    }

    public Client() {
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

    public Color getColor() { return Color; }

    public void setColor(Color toSet) { Color = toSet; }

    @Override
    public String toString() {
        return "{" +
                "\"Username\": \"" + Username + "\", " +
                "\"Handle\": \"" + Handle + "\", " +
                "\"Email\": \"" + Email + "\"" +
                //", \n\"Color\": \"" + Color + "\"" +
                "}";
    }
}