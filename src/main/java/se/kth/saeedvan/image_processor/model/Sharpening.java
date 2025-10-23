package se.kth.saeedvan.image_processor.model;

import se.kth.saeedvan.image_processor.utils.PixelConverter;

/**
 * A pixel processor that sharpens a grayscale image.
 * <p>
 *     This implementation applies the sharpening method, Unsharp Masking, by creating a blurred version of
 *     the image, subtracting it from the original and then adding the difference to the original.
 * </p>
 */
public class Sharpening implements IPixelProcessor {

    /**
     * Enhances the sharpness of the provided image pixel matrix using Unsharp Masking.
     * <p>
     * For each pixel, the method computes a new value by adding
     * the difference between the original and the blurred pixel to the original pixel.
     * Pixel values that fall outside the valid range (0–255) are either set to 0 or 255.
     * </p>
     *
     * @param originalPixels a 2D array representing the original image pixels
     * @return a new 2D array containing the sharpened image pixels
     */
    public int[][] processImage(int[][] originalPixels) {
        int height = originalPixels.length;
        int width = originalPixels[0].length;
        int blueValue = 0, redValue = 0, greenValue = 0;

        int[][] processedPixels = new int[height][width];
        int[][] bluredPixels = new ImageModel().processBlur(originalPixels);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                blueValue = (2 * PixelConverter.getBlue(originalPixels[i][j]))
                            - PixelConverter.getBlue(bluredPixels[i][j]);
                blueValue = (blueValue < 0) ? 0 : (blueValue > 255) ? 255 : blueValue;

                greenValue = (2 * PixelConverter.getGreen(originalPixels[i][j]))
                              - PixelConverter.getGreen(bluredPixels[i][j]);
                greenValue = (greenValue < 0) ? 0 : (greenValue > 255) ? 255 : greenValue;

                redValue = (2 * PixelConverter.getRed(originalPixels[i][j]))
                            - PixelConverter.getRed(bluredPixels[i][j]);
                redValue = (redValue < 0) ? 0 : (redValue > 255) ? 255 : redValue;
                processedPixels[i][j] = PixelConverter.toArgbPixel(PixelConverter.getAlpha(originalPixels[i][j]),
                                        redValue, greenValue, blueValue);
            }
        }
        return processedPixels;
    }
}