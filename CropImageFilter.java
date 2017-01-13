package part3;

import java.awt.image.BufferedImage;

/**
 * This is a crop filter, this filter will create a sub image with the given
 * paramaters for X,Y, width, and height
 * 
 * @author ShahidBilal Razzaq
 *
 */
public class CropImageFilter extends ImageRegionFilter {

	public BufferedImage filter(BufferedImage i) {
		// Sets up the width and height
		int width = getMaxX() - getMinX();
		int height = getMaxY() - getMinY();
		// creates a new sub image
		BufferedImage result = i.getSubimage(getMinX(), getMinY(), width, height);
		return result;

	}

}
