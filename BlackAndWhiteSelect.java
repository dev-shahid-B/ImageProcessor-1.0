package part3;

import java.awt.image.BufferedImage;

/**
 * Custom Filter for Black and white to be used for selection filter, while rest
 * of image is preserved.
 * 
 * @author ShahidBilal
 *
 */
public class BlackAndWhiteSelect extends ImageRegionFilter {

	public BufferedImage filter(BufferedImage i) {

		BufferedImage result = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB);

		// Iterates through each pixel in image matrix

		for (int y = 0; y < i.getHeight(); y++)
			for (int x = 0; x < i.getWidth(); x++) {
				//IfStatement that will select only region of image to be filtered
				if (y >= getMinY() && y <= getMaxY() && x >= getMinX() && x <= getMaxX()) {
					int pixel = i.getRGB(x, y);
					int a = (pixel >> 24) & 0xff;
					int redAmount = (pixel >> 16) & 0xff;
					int greenAmount = (pixel >> 8) & 0xff;
					int blueAmount = (pixel >> 0) & 0xff;

					// Average amount of color in pixels:

					int avg = (redAmount + greenAmount + blueAmount) / 3;

					// Compose the new pixel with averages of the pixel color
					// values
					// to give the Black and White effect

					int newPixel = (a << 24) | (avg << 16) | (avg << 8) | avg;

					// Set the pixel of the new image.
					result.setRGB(x, y, newPixel);
				} else
					result.setRGB(x, y, i.getRGB(x, y));
			}

		return result;

	}

}
