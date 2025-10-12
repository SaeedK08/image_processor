module se.kth.saeedvan.image_processor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens se.kth.saeedvan.image_processor to javafx.fxml;
    exports se.kth.saeedvan.image_processor;
}