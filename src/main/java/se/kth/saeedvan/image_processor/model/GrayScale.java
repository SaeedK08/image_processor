package se.kth.saeedvan.image_processor.model;

import se.kth.saeedvan.image_processor.utils.PixelConverter;

/**
 * A pixel processor that converts a color image to grayscale.
 * <p>
 *  This implementation averages the RGB components of each pixel
 *  to compute a single intensity value, resulting in a grayscale image.
 * </p>
 */
public class GrayScale implements IPixelProcessor {

    /**
     * Converts each pixel in the original pixel matrix to grayscale by averaging its RGB components.
     * @param originalPixels a 2D array where each pixel value encodes RGB color.
     * @return  a new 2D array where each pixel is replaced with its grayscale intensity.
     */
    public int[][] processImage(int[][] originalPixels) {
        int height = originalPixels.length;
        int width = originalPixels[0].length;

        int[][] processedPixels = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = originalPixels[i][j];
                double grayPixel = (double) (PixelConverter.getGreen(pixel) + PixelConverter.getBlue(pixel) + PixelConverter.getRed(pixel)) / 3.0;
                processedPixels[i][j] = PixelConverter.toArgbPixel(PixelConverter.getAlpha(pixel), (int)grayPixel, (int)grayPixel, (int)grayPixel);
            }
        }
        return processedPixels;
    }

}