package se.kth.saeedvan.image_processor.model;

public interface IPixelProcessor {
    int[][] processImage(int[][] originalPixels);
}