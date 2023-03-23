module com.example.yahtzeefx {
    requires javafx.controls;
    requires javafx.fxml;


    opens be.kdg.yahtzeefx to javafx.graphics;
    exports be.kdg.yahtzeefx;
}