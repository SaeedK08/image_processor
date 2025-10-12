package se.kth.saeedvan.image_processor.model;


import se.kth.saeedvan.image_processor.utils.PixelConverter;

public class ImageModel {
    private final RotateImage rotate;
    private final InvertColors invertColors;
    private final GrayScale grayScale;
    private final Blur blur;
    private final Sharpening sharpening;
    private final WindowLevel windowLevel;
    private int[][] processedPixels = null;


    public ImageModel() {
        rotate = new RotateImage();
        invertColors = new InvertColors();
        grayScale = new GrayScale();
        blur = new Blur();
        sharpening = new Sharpening();
        windowLevel = new WindowLevel();
    }

    public int[][] processRotateImage(int[][] pixels) {
        return rotate.processImage(pixels);
    }

    public int[][] processInvertColors(int[][] pixels) {
        return invertColors.processImage(pixels);
    }

    public int[][] processGrayScale(int[][] pixels) {
        return grayScale.processImage(pixels);
    }

    public int[][] processBlur(int[][] pixels) {
        return blur.processImage(pixels);
    }

    public int[][] processSharpening(int[][] pixels) {
        return sharpening.processImage(pixels);
    }

    public int[][] processWindowLevel(int[][] pixels, int window, int level) {
        windowLevel.setProperties(window, level);
        return windowLevel.processImage(pixels);
    }
}
