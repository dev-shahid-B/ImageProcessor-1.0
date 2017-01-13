package part3;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
/**
 * Class to create a new zoomed Image
 * @author ShahidBilal,
 * with help from tutorials on Java2s.com, Java-tips.org
 *
 */
public class ZoomImage implements ImageFilter{
	private int zoomValue;
	/**
	 * Sets the zoom value of the image
	 * @param c--imput integer zoom value
	 */
	public void setZoomValue(int c){
		zoomValue =c;
	}
	
	public BufferedImage filter(BufferedImage i) {
		int zoomedWidth = i.getWidth() * zoomValue;
		int zoomedHeight = i.getHeight() * zoomValue;
		BufferedImage zoomedImage = new BufferedImage(zoomedWidth, zoomedHeight,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = zoomedImage.createGraphics();
		g.drawImage(i, 0, 0, zoomedWidth, zoomedHeight, null);
		g.dispose();
		return null;
	}
	

}
