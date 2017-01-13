package part3;

import java.awt.image.BufferedImage;

/**
 * This Class creates a new BufferedImage which is rotated counterclockwise
 * 
 * @author ShahidBilal Razzaq 4-14-2016
 *
 */
public class RotateCounterClockwise implements ImageFilter {

	/**
	 * Rotates the image counterclockwise
	 */
	public BufferedImage filter(BufferedImage i) {
		int width = i.getWidth();
		int height = i.getHeight();
		BufferedImage result = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);

		// Creates a new Buffered Image with the X(width) values changed so they
		// write the new image backwards to create a counter clockwise rotated
		// effect
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {

				result.setRGB(y, width - x - 1, i.getRGB(x, y));
			}

		return result;
	}

}
