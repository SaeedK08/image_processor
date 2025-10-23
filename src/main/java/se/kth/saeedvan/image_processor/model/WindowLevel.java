package se.kth.saeedvan.image_processor.model;


import se.kth.saeedvan.image_processor.utils.PixelConverter;

/**
 * A pixel processor that adjusts the contrast of a grayscale image.
 * <p>
 * This implementation uses the Window/Level method to make details
 * in a certain intensity range more visible.
 * </p>
 */
public class WindowLevel implements IPixelProcessor {
    private int k, level, range;

    /**
     * Adjusts the contrast of the given image pixel matrix using the current window and level.
     * <p>
     * Pixels below the level are set to 0 (black), pixels above the window
     * are set to 255 (white), and pixels in between are scaled to 0–255.
     * </p>
     *
     * @param originalPixels a 2D array of grayscale image pixels
     * @return a new 2D array with adjusted contrast
     */
    public int[][] processImage(int[][] originalPixels) {
        int height = originalPixels.length;
        int width = originalPixels[0].length;

        int[][] processedPixels = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = PixelConverter.getBlue(originalPixels[i][j]);
                int alpha = PixelConverter.getAlpha(originalPixels[i][j]);
                if (pixel < level)
                    pixel = 0;
                else if (pixel > range)
                    pixel = 255;
                else
                    pixel = k * (pixel - level);
                processedPixels[i][j] = PixelConverter.toArgbPixel(alpha, pixel, pixel, pixel);
            }
        }
        return processedPixels;
    }

    /**
     * Sets the parameters for the Window/Level adjustment.
     * <p>
     * The window defines the range of pixel intensities that will be
     * expanded to the full 0–255 range, while the level defines the
     * midpoint of that range.
     * </p>
     *
     * @param window the intensity range (width) around the level to enhance
     * @param level  the midpoint intensity around which the contrast adjustment is centered
     */
    public void setProperties(int window, int level) {
        this.level = level;
        k = 255 / window;
        range = window + level;
    }
}
