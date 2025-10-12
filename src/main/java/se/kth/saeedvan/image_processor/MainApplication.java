package se.kth.saeedvan.image_processor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.kth.saeedvan.image_processor.model.ImageModel;
import se.kth.saeedvan.image_processor.view.Controller;
import se.kth.saeedvan.image_processor.view.View;

public class MainApplication extends Application {

    public void start(Stage stage) {
        View view = new View();
        ImageModel imageModel = new ImageModel();
        Controller controller = new Controller(view, imageModel);
        Scene scene = new Scene(view, 1080, 720);
        stage.setTitle("Image Processor");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}