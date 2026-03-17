module com.example.escriturarapida {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;

    opens com.example.escriturarapida to javafx.fxml;
    opens com.example.escriturarapida.controller to javafx.fxml;

    exports com.example.escriturarapida;
    opens com.example.escriturarapida.model to javafx.fxml;
}