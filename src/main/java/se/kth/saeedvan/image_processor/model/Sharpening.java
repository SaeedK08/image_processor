package se.kth.saeedvan.image_processor.model;

import se.kth.saeedvan.image_processor.utils.PixelConverter;

public class Sharpening implements IPixelProcessor {
    public int[][] processImage(int[][] originalPixels) {
        int height = originalPixels.length;
        int width = originalPixels[0].length;
        int blueValue = 0, redValue = 0, greenValue = 0;

        int[][] processedPixels = new int[height][width];
        int[][] bluredPixels = new ImageModel().getBlur().processImage(originalPixels);
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