package part3;

import java.awt.image.BufferedImage;

/**
 * This Class represents an image filter that will create a blurred effect on an
 * image and make a new bufferedimage
 * 
 * @author ShahidBilal Razzaq 4-14-2016
 *
 */
public class BlurFilter implements ImageFilter {
	public BufferedImage filter(BufferedImage i) {

		BufferedImage result = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB);

		int height = i.getHeight();
		int width = i.getWidth();
		// Iterate through each pixel of the Image

		for (int x = 1; x < width - 1; x++) {
			for (int y = 1; y < height - 1; y++) {

				// Set Initial RGB amounts to 0
				int redAmount = 0;
				int greenAmount = 0;
				int blueAmount = 0;

				// Set Each Pixel to the North, East, West, South, North-East,
				// North-West, South-East, South-West to the RGB Values
				int p1 = i.getRGB(x - 1, y - 1);
				redAmount += (p1 >> 16) & 0xff;
				greenAmount += (p1 >> 8) & 0xff;
				blueAmount += (p1 >> 0) & 0xff;

				int p2 = i.getRGB(x, y - 1);
				redAmount += (p2 >> 16) & 0xff;
				greenAmount += (p2 >> 8) & 0xff;
				blueAmount += (p2 >> 0) & 0xff;

				int p3 = i.getRGB(x + 1, y - 1);
				redAmount += (p3 >> 16) & 0xff;
				greenAmount += (p3 >> 8) & 0xff;
				blueAmount += (p3 >> 0) & 0xff;

				int p4 = i.getRGB(x - 1, y);
				redAmount += (p4 >> 16) & 0xff;
				greenAmount += (p4 >> 8) & 0xff;
				blueAmount += (p4 >> 0) & 0xff;

				// The Middle (Center) Pixel
				int p5 = i.getRGB(x, y);
				redAmount += (p5 >> 16) & 0xff;
				greenAmount += (p5 >> 8) & 0xff;
				blueAmount += (p5 >> 0) & 0xff;

				int p6 = i.getRGB(x + 1, y);
				redAmount += (p6 >> 16) & 0xff;
				greenAmount += (p6 >> 8) & 0xff;
				blueAmount += (p6 >> 0) & 0xff;

				int p7 = i.getRGB(x - 1, y + 1);
				redAmount += (p7 >> 16) & 0xff;
				greenAmount += (p7 >> 8) & 0xff;
				blueAmount += (p7 >> 0) & 0xff;

				int p8 = i.getRGB(x, y + 1);
				redAmount += (p8 >> 16) & 0xff;
				greenAmount += (p8 >> 8) & 0xff;
				blueAmount += (p8 >> 0) & 0xff;

				int p9 = i.getRGB(x + 1, y + 1);
				redAmount += (p9 >> 16) & 0xff;
				greenAmount += (p9 >> 8) & 0xff;
				blueAmount += (p9 >> 0) & 0xff;

				// Take The average of all the 9 Pixel's RGB Values
				redAmount /= 9;
				greenAmount /= 9;
				blueAmount /= 9;

				// Create a New Pixel
				int newPixel = (redAmount << 16) | (greenAmount << 8) | blueAmount;
				result.setRGB(x, y, newPixel);

			}
		}

		return result;

	}

}
