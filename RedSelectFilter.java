package part3;

import java.awt.image.BufferedImage;

/**
 * This is an Extra Class which averages green and blue values, while keeping
 * red values true for an interesting effect.
 * 
 * @author ShahidBilal Razzaq
 *
 */
public class RedSelectFilter implements ImageFilter {

	public BufferedImage filter(BufferedImage i) {

		BufferedImage result = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB);

		// Iterates Through each pixel
		for (int y = 0; y < i.getHeight(); y++)
			for (int x = 0; x < i.getWidth(); x++) {

				int pixel = i.getRGB(x, y);

				int redAmount = (pixel >> 16) & 0xff;
				int greenAmount = (pixel >> 8) & 0xff;
				int blueAmount = (pixel >> 0) & 0xff;

				// Average amount of color in pixels:

				int avg = (redAmount + greenAmount + blueAmount) / 3;

				// if any green or blue exist in the pixel, sets the amounts to
				// average
				if (greenAmount > 0 || blueAmount > 0) {
					greenAmount = avg;
					blueAmount = avg;

				}

				// Compose the new pixel.
				int newPixel = (redAmount << 16) | (greenAmount << 8) | blueAmount;

				// Set the pixel of the new image.
				result.setRGB(x, y, newPixel);
			}

		return result;

	}

}
