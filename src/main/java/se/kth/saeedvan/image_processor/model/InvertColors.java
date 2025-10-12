package se.kth.saeedvan.image_processor.model;

import se.kth.saeedvan.image_processor.utils.PixelConverter;

public class InvertColors implements IPixelProcessor{
    public int[][] processImage(int[][] originalPixels) {
        int height = originalPixels.length;
        int width = originalPixels[0].length;

        int[][] processedPixels = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = originalPixels[i][j];
                int alpha = PixelConverter.getAlpha(pixel);
                int invGreen = 255 - PixelConverter.getGreen(pixel);
                int invRed = 255 - PixelConverter.getRed(pixel);
                int invBlue = 255 - PixelConverter.getBlue(pixel);
                processedPixels[i][j] = PixelConverter.toArgbPixel(alpha, invRed, invGreen, invBlue);
            }
        }
        return processedPixels;
    }
}

