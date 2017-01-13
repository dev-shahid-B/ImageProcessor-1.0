package part3;

import java.awt.image.BufferedImage;

/**
 * Custom filter to be used with the selection feature
 * 
 * @author ShahidBilal
 *
 */
public class GreenBlueSwapSelect extends ImageRegionFilter {

	public BufferedImage filter(BufferedImage i) {

		BufferedImage result = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB);

		// Iterates Through each pixel
		for (int y = 0; y < i.getHeight(); y++)
			for (int x = 0; x < i.getWidth(); x++) {
				//IfStatement that will select only region of image to be filtered
				if (y >= getMinY() && y <= getMaxY() && x >= getMinX() && x <= getMaxX()) {

					int pixel = i.getRGB(x, y);

					int redAmount = (pixel >> 16) & 0xff;
					int greenAmount = (pixel >> 8) & 0xff;
					int blueAmount = (pixel >> 0) & 0xff;

					// Swap Green with blue and blue with green
					int newPixel = (redAmount << 16) | (blueAmount << 8) | greenAmount;

					// Set the pixel of the new image.
					result.setRGB(x, y, newPixel);
				} else
					result.setRGB(x, y, i.getRGB(x, y));
			}

		return result;

	}

}
