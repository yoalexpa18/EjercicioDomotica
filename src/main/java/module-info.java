module org.example.philips_hue {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens org.example.philips_hue to javafx.fxml;
    exports org.example.philips_hue;
}