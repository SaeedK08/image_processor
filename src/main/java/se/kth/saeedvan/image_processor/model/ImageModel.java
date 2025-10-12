package se.kth.saeedvan.image_processor.model;


import se.kth.saeedvan.image_processor.utils.PixelConverter;

public class ImageModel {
    private final RotateImage rotateImage;
    private final InvertColors invertColors;
    private final GrayScale grayScale;
    private final Blur blur;
    private final Sharpening sharpening;
    private final WindowLevel windowLevel;


    public ImageModel() {
        rotateImage = new RotateImage();
        invertColors = new InvertColors();
        grayScale = new GrayScale();
        blur = new Blur();
        sharpening = new Sharpening();
        windowLevel = new WindowLevel();
    }

    public RotateImage getRotateImage() {
        return rotateImage;
    }

    public InvertColors getInvertColors() {
        return invertColors;
    }

    public GrayScale getGrayScale() {
        return grayScale;
    }

    public Blur getBlur() {
        return blur;
    }

    public Sharpening getSharpening() {
        return sharpening;
    }

    public WindowLevel getWindowLevel() {
        return windowLevel;
    }

    public int[][] getHistogramValues(int[][] pixels) {
        int[][] histogramValues = new int[256][3];

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                histogramValues[PixelConverter.getRed(pixels[i][j])][0]++;
                histogramValues[PixelConverter.getGreen(pixels[i][j])][1]++;
                histogramValues[PixelConverter.getBlue(pixels[i][j])][2]++;
            }
        }
        return histogramValues;
    }
}
