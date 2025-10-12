package se.kth.saeedvan.image_processor.model;

import se.kth.saeedvan.image_processor.utils.PixelConverter;

public class GrayScale implements IPixelProcessor {
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