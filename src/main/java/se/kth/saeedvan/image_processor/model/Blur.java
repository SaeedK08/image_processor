package se.kth.saeedvan.image_processor.model;

import se.kth.saeedvan.image_processor.utils.PixelConverter;

import java.util.ArrayList;

public class Blur implements IPixelProcessor {
    public int[][] processImage(int[][] originalPixels) {
        int height = originalPixels.length;
        int width = originalPixels[0].length;
        ArrayList<Integer> neighborsPixelsValue = new ArrayList<>();

        int[][] processedPixels = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                neighborsPixelsValue = findNeighborsIndex(originalPixels,j,i);
                int pixelValue = calculateBlurValue(neighborsPixelsValue);
                processedPixels[i][j] = pixelValue;
            }
        }
        return processedPixels;
    }

    private ArrayList<Integer> findNeighborsIndex(int[][] array, int xPos, int yPos) {
        ArrayList<Integer> neighborsIndex = new ArrayList<>();
        int startIndY = (yPos >= array.length - 1) ? (yPos - 2) : (yPos == 0) ? 0 : yPos - 1;
        int startIndX = (xPos >= array[0].length - 1) ? (xPos - 2) : (xPos == 0) ? 0 : xPos - 1;
        for (int i = startIndY; i < startIndY + 3; i++) {
            for (int j = startIndX; j < startIndX + 3; j++) {
                neighborsIndex.add(array[i][j]);
            }
        }

        return neighborsIndex;
    }

    private int calculateBlurValue(ArrayList<Integer> list) {
        int blueValue, redValue, greenValue, alphaValue;
        blueValue=redValue=greenValue=alphaValue=0;

        for (int i = 0; i < list.size(); i++) {
           redValue += PixelConverter.getRed(list.get(i));
           blueValue += PixelConverter.getBlue(list.get(i));
           greenValue += PixelConverter.getGreen(list.get(i));
           alphaValue += PixelConverter.getAlpha(list.get(i));
        }
        redValue /= list.size();
        blueValue /= list.size();
        greenValue /= list.size();
        alphaValue /= list.size();

        return PixelConverter.toArgbPixel(alphaValue,redValue, greenValue, blueValue);
    }
}