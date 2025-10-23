package se.kth.saeedvan.image_processor.model;

/**
 * A facade class for all image processing operations.
 * <p>
 * This class provides simple methods to apply different image processors
 * such as rotation, grayscale, inversion, blur, sharpening, and window/level
 * contrast adjustment. It serves as an interface to the processing model.
 * </p>
 */
public class ImageModel {
    private final RotateImage rotate;
    private final InvertColors invertColors;
    private final GrayScale grayScale;
    private final Blur blur;
    private final Sharpening sharpening;
    private final WindowLevel windowLevel;


    public ImageModel() {
        rotate = new RotateImage();
        invertColors = new InvertColors();
        grayScale = new GrayScale();
        blur = new Blur();
        sharpening = new Sharpening();
        windowLevel = new WindowLevel();
    }

    /**
     * Rotates the given image by 180 degrees.
     *
     * @param pixels a 2D array of image pixels
     * @return a new 2D array with the rotated image
     */
    public int[][] processRotateImage(int[][] pixels) {
        return rotate.processImage(pixels);
    }

    /**
     * Inverts the colors of the given image.
     *
     * @param pixels a 2D array of image pixels
     * @return a new 2D array with inverted colors
     */
    public int[][] processInvertColors(int[][] pixels) {
        return invertColors.processImage(pixels);
    }

    /**
     * Converts the given image to grayscale.
     *
     * @param pixels a 2D array of image pixels
     * @return a new 2D array with grayscale values
     */
    public int[][] processGrayScale(int[][] pixels) {
        return grayScale.processImage(pixels);
    }

    /**
     * Applies a blur effect to the given image.
     *
     * @param pixels a 2D array of image pixels
     * @return a new 2D array with blurred pixels
     */
    public int[][] processBlur(int[][] pixels) {
        return blur.processImage(pixels);
    }

    /**
     * Sharpens the given image using the sharpening processor.
     *
     * @param pixels a 2D array of image pixels
     * @return a new 2D array with sharpened pixels
     */
    public int[][] processSharpening(int[][] pixels) {
        return sharpening.processImage(pixels);
    }

    /**
     * Adjusts the contrast of the given image using Window/Level.
     *
     * @param pixels a 2D array of image pixels
     * @param window the intensity range to enhance
     * @param level the midpoint of the range
     * @return a new 2D array with contrast-adjusted pixels
     */
    public int[][] processWindowLevel(int[][] pixels, int window, int level) {
        windowLevel.setProperties(window, level);
        return windowLevel.processImage(pixels);
    }
}
