package se.kth.saeedvan.image_processor.model;

import se.kth.saeedvan.image_processor.utils.PixelConverter;

public class HistogramGenerator {
    public static int[][] generate(int[][] pixels) {
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
