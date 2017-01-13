package part3;
import java.awt.image.BufferedImage;
/**
 * This is an Extra Filter Class which averages red and blue values, and keeps green values the same to create an interesting effect.
 * @author ShahidBilal
 *
 */
public class GreenSelectFilter implements ImageFilter{
	
	public BufferedImage filter(BufferedImage i) {
		
		BufferedImage result = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB);

		// Iterates Through each pixel
		for(int y = 0; y < i.getHeight(); y++)
			for(int x = 0; x < i.getWidth(); x++) {

				int pixel = i.getRGB(x, y);
				
				int redAmount = (pixel >> 16) & 0xff;
				int greenAmount = (pixel >> 8) & 0xff;
				int blueAmount = (pixel >> 0) & 0xff;
		
				//Average amount of color in pixels:
				
				int avg = (redAmount+greenAmount+blueAmount) /3;
				
				
				//if any red or blue exist in picture, sets them to average
				if(redAmount >0 || blueAmount>0){
					redAmount = avg;
					blueAmount = avg;
					
				}
				
		
				
				// create a new pixel
				int newPixel = (redAmount << 16 ) | (greenAmount << 8) | blueAmount;
				
				// Set the pixel of the new image.
				result.setRGB(x, y, newPixel);
			}
		
		return result;
	
	}
}
