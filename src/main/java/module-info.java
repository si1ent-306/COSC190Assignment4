module org.example.cosc190assignment4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.example.cosc190assignment4 to javafx.fxml;
    exports org.example.cosc190assignment4;
}