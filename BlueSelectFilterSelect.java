package part3;

import java.awt.image.BufferedImage;

/**
 * Custom filter to be used with selection feature
 * 
 * @author ShahidBilal
 *
 */
public class BlueSelectFilterSelect extends ImageRegionFilter {
	public BufferedImage filter(BufferedImage i) {

		BufferedImage result = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB);

		// Iterates through each pixel in the image
		for (int y = 0; y < i.getHeight(); y++)
			for (int x = 0; x < i.getWidth(); x++) {
				//IfStatement that will select only region of image to be filtered
				if (y >= getMinY() && y <= getMaxY() && x >= getMinX() && x <= getMaxX()) {

					int pixel = i.getRGB(x, y);

					int redAmount = (pixel >> 16) & 0xff;
					int greenAmount = (pixel >> 8) & 0xff;
					int blueAmount = (pixel >> 0) & 0xff;

					// Average ammount of color in pixels:

					int avg = (redAmount + greenAmount + blueAmount) / 3;

					// if red or green amounts exist, set them to the average
					// color of RGB

					if (redAmount > 0 || greenAmount > 0) {
						redAmount = avg;
						greenAmount = avg;

					}

					// Compose the new pixel.
					int newPixel = (redAmount << 16) | (greenAmount << 8) | blueAmount;

					// Set the pixel of the new image.
					result.setRGB(x, y, newPixel);
				} else
					result.setRGB(x, y, i.getRGB(x, y));
			}

		return result;

	}

}
