package part3;

import java.awt.image.BufferedImage;

/**
 * This Class creates a filter which swaps red and blue values giving an
 * interesting effect.
 * 
 * @author ShahidBilal Razzaq
 *
 */
public class RedBlueSwapFilter implements ImageFilter {

	public BufferedImage filter(BufferedImage i) {

		BufferedImage result = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB);

		// Iterates through each pixel
		for (int y = 0; y < i.getHeight(); y++)
			for (int x = 0; x < i.getWidth(); x++) {

				int pixel = i.getRGB(x, y);

				int redAmount = (pixel >> 16) & 0xff;
				int greenAmount = (pixel >> 8) & 0xff;
				int blueAmount = (pixel >> 0) & 0xff;

				// Swap red and blue values, and create a new pixel
				int newPixel = (blueAmount << 16) | (greenAmount << 8) | redAmount;

				// Set the pixel of the new image.
				result.setRGB(x, y, newPixel);
			}

		return result;

	}

}