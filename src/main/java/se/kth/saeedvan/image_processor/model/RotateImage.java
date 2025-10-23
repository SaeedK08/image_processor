package se.kth.saeedvan.image_processor.model;

/**
 * A pixel processor that rotates an image 180 degree.
 * <p>
 *         This implementation reverses the pixel order both vertically and horizontally,
 *         flipping the image upside down and mirroring it.
 * </p>
 */
public class RotateImage implements IPixelProcessor{

    /**
     * Rotates the given pixel matrix by 180 degrees.
     * <p>
     *      Each pixel from the original image is placed in the opposite position
     *      in the new matrix, effectively inverting both axes.
     * </p>
     *
     * @param originalPixels a 2D array where each pixel value encodes RGB color.
     * @return  a new 2D array containing the rotated image pixels
     */
    public int[][] processImage(int[][] originalPixels) {
        int height = originalPixels.length;
        int width = originalPixels[0].length;

        int[][] processedPixels = new int[height][width];
        int row = 0;
        int col = 0;

        for (int i = height - 1; i >= 0; i--) {
            for (int j = width - 1; j >= 0; j--) {
               processedPixels[row][col] =  originalPixels[i][j];
               col++;
            }
            row++;
            col = 0;
        }
        return processedPixels;
    }
}