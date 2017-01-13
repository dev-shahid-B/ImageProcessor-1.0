package part3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/** 
 * A JPanel for displaying a BufferedImage.
 * Also listens for mouse events to allow user to select a region of the image.
 *
 * @author Erin Parker, last updated 4/20/16
 * some code modified by Shahid
 */
public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener {

	private BufferedImage image;
	
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	private static final long serialVersionUID = 1L;
	
	public ImagePanel(BufferedImage img) {
		image = img;
		setPreferredSize(new Dimension(img.getWidth(), image.getHeight()));
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void mouseClicked(MouseEvent arg0) {
		// unused method
	}

	public void mouseEntered(MouseEvent arg0) {
		// unused method		
	}

	public void mouseExited(MouseEvent arg0) {
		// unused method		
	}
	
	/** 
	 * The method sets the minX and minY to the location where the mouse 
	 * is first pressed.
	 */
	public void mousePressed(MouseEvent e) {
		minX = e.getX();
		minY = e.getY();
	}

	/**
	 * This method prints out X,Y information useful during development.
	 */
	public void mouseReleased(MouseEvent arg0) {
		
		
	}

	/**
	 * This method sets the maxX and maxY values as the mouse is dragged.
	 */
	public void mouseDragged(MouseEvent e) {
		maxX = e.getX();
		maxY = e.getY();
		int minValueX = maxX;
		int minValueY = maxY;
		
		if(maxX<minX && maxY<minY){
			minX = maxX;
			maxX = minValueX;
			minY = maxY;
			maxY = minValueY;
			repaint();
		}else{
			repaint();
		}
		
		
	
		
		
		
	}

	public void mouseMoved(MouseEvent arg0) {
		// unused method		
	}
	
	/**
	 * This method paints the image panel, which includes painting a
	 * gray box to visually indicated the region being selected.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		g.setColor(new Color(105, 105, 105, 125));
		g.fillRect(minX, minY, maxX - minX, maxY - minY);
		
		
	}

	/**
	 * Getter for the selected region.
	 * 
	 * @return a Region2d object that represents the region selected on the panel
	 */
	public Region2d getSelectedRegion() {
		return new Region2d(minX, maxX, minY, maxY);
	}
}