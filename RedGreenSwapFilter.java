package part3;

import java.awt.image.BufferedImage;

/**
 * This Class creates a filter which swaps red and green values to make an
 * interesting effect
 * 
 * @author ShahidBilal razzaq
 *
 */
public class RedGreenSwapFilter implements ImageFilter {

	public BufferedImage filter(BufferedImage i) {

		BufferedImage result = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB);

		// Iterates Through each pixel
		for (int y = 0; y < i.getHeight(); y++)
			for (int x = 0; x < i.getWidth(); x++) {

				int pixel = i.getRGB(x, y);

				int redAmount = (pixel >> 16) & 0xff;
				int greenAmount = (pixel >> 8) & 0xff;
				int blueAmount = (pixel >> 0) & 0xff;

				// swap red and green values and compose the pixel
				int newPixel = (greenAmount << 16) | (redAmount << 8) | blueAmount;

				// Set the pixel of the new image.
				result.setRGB(x, y, newPixel);
			}

		return result;

	}
}