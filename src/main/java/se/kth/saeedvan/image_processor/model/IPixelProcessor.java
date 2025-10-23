package se.kth.saeedvan.image_processor.model;

/**
 * This interface represents a generic pixel processor that applies pixel transformation for an image pixel matrix.
 */
public interface IPixelProcessor {
    /**
     * Apply a transformation to an image pixel matrix.
     * @param originalPixels an image pixel matrix to be processed.
     * @return  a processed pixel matrix for that image.
     */
    int[][] processImage(int[][] originalPixels);
}