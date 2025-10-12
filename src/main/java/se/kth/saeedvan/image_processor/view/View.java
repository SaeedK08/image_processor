package se.kth.saeedvan.image_processor.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import se.kth.saeedvan.image_processor.io.FileIO;
import java.io.File;


public class View extends VBox {
    private HistogramView hV;
    private BorderPane bP;
    private ScrollPane sP;
    private MenuBar menuBar;
    private Image image;
    private ImageView imageView;
    private Menu fileTab;
    private Menu processTab;
    private Menu helpTab;
    private FileChooser fileChooser;
    private Slider windowSlider, levelSlider;

    public View() {
        hV = new HistogramView();
        initScrollPane();
        intiBp();
        initSliders();
        initMenuBar();
        this.getChildren().addAll(menuBar, bP);
    }

    public void addEventhandlers(Controller controller) {
        processTab.getItems().get(0).setOnAction(event -> {
            controller.handleRotateImage(imageView.getImage());
        });
        processTab.getItems().get(1).setOnAction(event -> {
            controller.handleIvertColors(imageView.getImage());
        });
        processTab.getItems().get(2).setOnAction(event -> {
            controller.handleGrayScale(imageView.getImage());
        });
        processTab.getItems().get(5).setOnAction(event -> {
            controller.handleBlur(imageView.getImage());
        });
        processTab.getItems().get(4).setOnAction(event -> {
            controller.handleSharpening(imageView.getImage());
        });
        processTab.getItems().get(6).setOnAction(event -> {
            controller.handleOriginal();
        });
        processTab.getItems().get(3).setOnAction(event -> {
            int windowValue = (int) windowSlider.getValue();
            if (windowValue <= 0) {
                setAlert("Window value need to be set", Alert.AlertType.WARNING);
                return;
            }
            int levelValue = (int) levelSlider.getValue();
            controller.handleWindowLevel(imageView.getImage(), windowValue, levelValue);
        });

        fileTab.getItems().getFirst().setOnAction(event -> {
            File imageFile = fileChooser.showOpenDialog(this.getScene().getWindow());
            if (imageFile == null) {
                setAlert("No image was found", Alert.AlertType.ERROR);
                return;
            }
            image = FileIO.readImage(imageFile);
            imageView = new ImageView(image);
            sP.setContent(imageView);
            controller.convertAndView(image, null);
            for(MenuItem mi : processTab.getItems()) { mi.setDisable(false); }
            fileTab.getItems().get(1).setDisable(false);
            controller.setOriginalImage(image);

        });

        fileTab.getItems().get(1).setOnAction(event -> {
            File file = fileChooser.showSaveDialog(this.getScene().getWindow());
            if (file == null) {
                setAlert("No file was chosen", Alert.AlertType.WARNING);
                return;
            }
            if (!FileIO.writeImage(imageView.getImage(), file)) {
                setAlert("Could not save image", Alert.AlertType.ERROR);
            }
        });

        fileTab.getItems().get(2).setOnAction(event -> {System.exit(0);});
    }

    public void updateImage(Image image) {
        imageView.setImage(image);
    }

    private void initScrollPane() {
        sP = new ScrollPane(imageView);
    }

    private void intiBp() {
        bP = new BorderPane();
        bP.setLeft(hV);
        bP.setCenter(sP);
        bP.setPadding(new Insets(20,20,20,20));

    }

    private void initSliders() {
        HBox hB = new HBox();
        VBox wVB, lVB;
        wVB = new VBox();
        lVB = new VBox();
        levelSlider = new Slider();
        levelSlider.setMinWidth(240);
        levelSlider.setMin(0);
        levelSlider.setMax(255);
        levelSlider.setShowTickLabels(true);
        levelSlider.setShowTickMarks(true);
        levelSlider.setMajorTickUnit(50);
        Label level = new Label("Level");
        level.setFont(new Font("Arial", 15));
        lVB.setSpacing(10);
        lVB.getChildren().addAll(levelSlider, level);

        windowSlider = new Slider();
        windowSlider.setMinWidth(240);
        windowSlider.setMin(0);
        windowSlider.setMax(255);
        windowSlider.setShowTickLabels(true);
        windowSlider.setShowTickMarks(true);
        windowSlider.setMajorTickUnit(50);
        Label window = new Label("Window");
        window.setFont(new Font("Arial", 15));
        wVB.getChildren().addAll(windowSlider, window);
        wVB.setSpacing(10);

        hB.setSpacing(15);
        hB.getChildren().addAll(wVB, lVB);
        bP.setBottom(hB);

    }

    private void setAlert(String msg, Alert.AlertType at) {
        Alert alert = new Alert(at);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void initMenuBar() {
        menuBar = new MenuBar();
        fileTab = new Menu("File");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setDisable(true);
        MenuItem exitItem = new MenuItem("Exit");
        fileTab.getItems().addAll(openItem, saveItem, exitItem);

        processTab = new Menu("Process");
        MenuItem rotateItem = new MenuItem("Rotate");
        MenuItem invertItem = new MenuItem("Invert");
        MenuItem garyScaleItem = new MenuItem("Gray Scale");
        MenuItem windowLevelItem = new MenuItem("Window/Level");
        MenuItem blurItem = new MenuItem("Blur");
        MenuItem sharpeningItem = new MenuItem("Sharpening");
        MenuItem restoreOriginalItem = new MenuItem("Restore Original");
        processTab.getItems().addAll(rotateItem, invertItem, garyScaleItem,
                windowLevelItem, sharpeningItem, blurItem, restoreOriginalItem);

        for(MenuItem mi : processTab.getItems()) { mi.setDisable(true); }

        helpTab = new Menu("Help");
        menuBar.getMenus().addAll(fileTab, processTab, helpTab);
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                "Image files", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.svg");
        fileChooser.getExtensionFilters().add(filter);
    }

    public HistogramView getHV() {
        return hV;
    }
}