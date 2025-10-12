package se.kth.saeedvan.image_processor.view;

import javafx.scene.image.Image;
import se.kth.saeedvan.image_processor.model.ImageModel;
import se.kth.saeedvan.image_processor.utils.ImagePixelsConverter;


public class Controller {
    private View view;
    private ImageModel imageModel;
    private Image original;

    public Controller (View view, ImageModel imageModel) {
        this.imageModel = imageModel;
        this.view = view;
        view.addEventhandlers(this);

    }

    public void handleRotateImage(Image image) {
        convertAndView(image, imageModel.getRotateImage().processImage(ImagePixelsConverter.imageToPixels(image)));
    }

    public void handleIvertColors(Image image) {
        convertAndView(image, imageModel.getInvertColors().processImage(ImagePixelsConverter.imageToPixels(image)));
    }

    public void handleGrayScale(Image image) {
        convertAndView(image, imageModel.getGrayScale().processImage(ImagePixelsConverter.imageToPixels(image)));
    }

    public void handleBlur(Image image) {
        convertAndView(image, imageModel.getBlur().processImage(ImagePixelsConverter.imageToPixels(image)));
    }

    public void handleSharpening(Image image) {
        convertAndView(image, imageModel.getSharpening().processImage(ImagePixelsConverter.imageToPixels(image)));
    }

    public void handleWindowLevel(Image image, int window, int level) {
        imageModel.getWindowLevel().setProperties(window, level);
        convertAndView(image, imageModel.getWindowLevel().processImage(ImagePixelsConverter.imageToPixels(image)));
    }

    public void handleOriginal() {
        convertAndView(original, null);
    }

    public void convertAndView(Image image, int [][] pixels) {
        if (pixels == null) {
            pixels = ImagePixelsConverter.imageToPixels(image);
        }
        int[][] histogramValues = new int[256][3];
        histogramValues = imageModel.getHistogramValues(pixels);
        image = ImagePixelsConverter.pixelsToImage(pixels);
        view.updateImage(image);
        view.getHV().updateView(histogramValues);
    }

    protected void setOriginalImage(Image original) {
        this.original = original;
    }
}