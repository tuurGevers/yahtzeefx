module com.example.yahtzeefx {
    requires javafx.controls;
    requires javafx.fxml;


    opens be.kdg.yahtzeefx to javafx.fxml;
    exports be.kdg.yahtzeefx;
}