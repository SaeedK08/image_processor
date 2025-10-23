package se.kth.saeedvan.image_processor.model;

import se.kth.saeedvan.image_processor.utils.PixelConverter;


/**
 * Generate values that are needed to draw the histogram.
 */
public class HistogramGenerator {

    /**
     * This method generates a 2D array that represents the frequency of intensity for
     * each RGB component in an image pixel matrix.
     * @param pixels a 2D array where each pixel value encodes RGB color.
     * @return  a 2D array with three columns red, green and blue and their intensity frequency.
     */
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
