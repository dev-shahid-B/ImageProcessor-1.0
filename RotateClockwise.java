package part3;

import java.awt.image.BufferedImage;

/**
 * This Class recreates a BufferedImage rotated Clockwise
 * 
 * @author ShahidBilal Razzaq 4-14-2016
 *
 */
public class RotateClockwise implements ImageFilter {

	/**
	 * Rotates an Image clockwise
	 */
	public BufferedImage filter(BufferedImage i) {
		int width = i.getWidth();
		int height = i.getHeight();
		BufferedImage result = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);

		// Iterates Through Each pixel, in a long-wise orientation, so the new image created has a horizontal clockwise orientation
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				result.setRGB(height - y - 1, x, i.getRGB(x, y));
			}

		return result;
	}

}
