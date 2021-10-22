module com.haziqfaiz.osassmntgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.haziqfaiz.osassmntgui to javafx.fxml;
    exports com.haziqfaiz.osassmntgui;
}