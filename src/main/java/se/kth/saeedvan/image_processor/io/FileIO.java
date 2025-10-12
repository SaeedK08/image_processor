package se.kth.saeedvan.image_processor.io;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileIO {
    public static Image readImage(File imageFile) {
        return new Image(imageFile.toURI().toString());
    }

    public static boolean writeImage(Image image, File file) {

        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        try {
            return ImageIO.write(bufferedImage, "png", file);
        }
        catch (IOException e) {
            return false;
        }
    }
}