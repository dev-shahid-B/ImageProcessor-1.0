package part3;
import java.awt.image.BufferedImage;


/**
 * This Class Creates a Green-Blue swap filter of each pixel color value, thus swapping green and blue in the picture
 * @author ShahidBilal
 *4-14-2016
 */
public class GreenBlueSwapFilter implements ImageFilter {

	public BufferedImage filter(BufferedImage i) {
		
		BufferedImage result = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB);

		// Iterates Through each pixel 
		for(int y = 0; y < i.getHeight(); y++)
			for(int x = 0; x < i.getWidth(); x++) {

				int pixel = i.getRGB(x, y);
				
				int redAmount = (pixel >> 16) & 0xff;
				int greenAmount = (pixel >> 8) & 0xff;
				int blueAmount = (pixel >> 0) & 0xff;
		
				
				
				
				// Swap Green with blue and blue with green
				int newPixel = (redAmount << 16 ) | (blueAmount << 8) | greenAmount;
				
				// Set the pixel of the new image.
				result.setRGB(x, y, newPixel);
			}
		
		return result;
	
	}

}