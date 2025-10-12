package se.kth.saeedvan.image_processor.view;

import javafx.scene.image.Image;
import se.kth.saeedvan.image_processor.model.HistogramGenerator;
import se.kth.saeedvan.image_processor.model.ImageModel;
import se.kth.saeedvan.image_processor.utils.ImagePixelsConverter;


public class Controller {
    private final View view;
    private final ImageModel imageModel;
    private Image original;
    private int[][] processedPixels = null;

    public Controller (View view, ImageModel imageModel) {
        this.imageModel = imageModel;
        this.view = view;
        view.addEventhandlers(this);

    }

    public void handleRotateImage(Image image) {
        processedPixels = imageModel.processRotateImage(ImagePixelsConverter.imageToPixels(image));
        signalUpdateToView(ImagePixelsConverter.pixelsToImage(processedPixels));
    }

    public void handleInvertColors(Image image) {
        processedPixels = imageModel.processInvertColors(ImagePixelsConverter.imageToPixels(image));
        signalUpdateToView(ImagePixelsConverter.pixelsToImage(processedPixels));
    }

    public void handleGrayScale(Image image) {
        processedPixels = imageModel.processGrayScale(ImagePixelsConverter.imageToPixels(image));
        signalUpdateToView(ImagePixelsConverter.pixelsToImage(processedPixels));
    }

    public void handleBlur(Image image) {
        processedPixels = imageModel.processBlur(ImagePixelsConverter.imageToPixels(image));
        signalUpdateToView(ImagePixelsConverter.pixelsToImage(processedPixels));
    }

    public void handleSharpening(Image image) {
        processedPixels = imageModel.processSharpening(ImagePixelsConverter.imageToPixels(image));
        signalUpdateToView(ImagePixelsConverter.pixelsToImage(processedPixels));
    }

    public void handleWindowLevel(Image image, int window, int level) {
        processedPixels = imageModel.processWindowLevel(ImagePixelsConverter.imageToPixels(image), window, level);
        signalUpdateToView(ImagePixelsConverter.pixelsToImage(processedPixels));
    }

    public void handleOriginal() {
        signalUpdateToView(original);
    }

    public void signalUpdateToView(Image image) {

        int[][] histogramValues = new int[256][3];
        histogramValues = HistogramGenerator.generate(ImagePixelsConverter.imageToPixels(image));
        view.updateImage(image);
        view.updateHV(histogramValues);
    }

    protected void setOriginalImage(Image original) {
        this.original = original;
    }
}