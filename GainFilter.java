package part3;

import java.awt.image.BufferedImage;

/**
 * Image filter that changes the contrast among pixels in an image by scaling
 * all color components by X.
 * 
 * X must be > 0. X < 1 decreases contrast and makes the image darker. X > 1
 * increases contrast and makes the image lighter.
 * 
 * @author Erin Parker & CS 1410 class, last updated 4/6/16 X value Changed by
 *         Shahid from Original value
 */
public class GainFilter implements ImageFilter {

	private double X ;
	/**
	 * method to set custom value for variable X
	 * @param c--input value from slider
	 */
	public void setGainValue(double c){
		X = c ;
	}

	public BufferedImage filter(BufferedImage img) {

		BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < img.getHeight(); y++)
			for (int x = 0; x < img.getWidth(); x++) {

				int pixel = img.getRGB(x, y);
				int redAmount = (pixel >> 16) & 0xff;
				int greenAmount = (pixel >> 8) & 0xff;
				int blueAmount = (pixel >> 0) & 0xff;

				redAmount *= X;
				if (redAmount > 255) {
					redAmount = 255;
				}

				greenAmount *= X;
				if (greenAmount > 255) {
					greenAmount = 255;
				}

				blueAmount *= X;
				if (blueAmount > 255) {
					blueAmount = 255;
				}

				int newPixel = (redAmount << 16) | (greenAmount << 8) | blueAmount;
				result.setRGB(x, y, newPixel);
			}

		return result;
	}
}
