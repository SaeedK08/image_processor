package se.kth.saeedvan.image_processor.model;

public class RotateImage implements IPixelProcessor{
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