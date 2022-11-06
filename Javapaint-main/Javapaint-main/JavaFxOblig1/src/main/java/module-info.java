module com.example.javafxoblig1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.javafxoblig1 to javafx.fxml;
    exports com.example.javafxoblig1;
}