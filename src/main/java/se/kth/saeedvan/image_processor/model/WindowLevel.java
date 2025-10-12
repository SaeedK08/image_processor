package se.kth.saeedvan.image_processor.model;


import se.kth.saeedvan.image_processor.utils.PixelConverter;

public class WindowLevel implements IPixelProcessor {
    private int k, level, range;

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
    public void setProperties(int window, int level) {
        this.level = level;
        k = 255 / window;
        range = window + level;
    }
}
