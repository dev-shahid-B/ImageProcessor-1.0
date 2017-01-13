package part3;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This Class setsup and launches and Image processing Application that allows a
 * user to apply pre-selected filters and save the file
 * 
 * @author ShahidBilal Razzaq April 26, 2016
 *
 */
public class ImageProcessor extends JFrame implements ActionListener, ChangeListener {

	/**
	 * Main Method for launching the Program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ImageProcessor launch = new ImageProcessor();
		launch.setVisible(true);

	}

	// Private Instance Variables:

	// Main Panel RadioButton Variables
	protected JRadioButton button1;
	protected JRadioButton button2;
	protected JRadioButton button3;
	protected JRadioButton button4;
	protected JRadioButton button5;
	protected JRadioButton button6;
	protected JRadioButton button7;
	protected JRadioButton button8;
	protected JRadioButton button9;
	protected JRadioButton button10;
	protected JRadioButton button11;
	protected JRadioButton button12;
	protected ButtonGroup filterGroup;
	protected JPanel radioButtons;

	// MultiSelection Radio Button
	protected JRadioButton On;
	protected JRadioButton Off;
	protected ButtonGroup multiSelect;
	protected JPanel multiSelectPanel;

	// Selective Filter Button
	protected JButton regionFilterButton;

	// GUI Variables

	protected JButton filterButton;
	protected JButton cropButton;
	protected JMenuItem openImageOption;
	protected JMenuItem saveImageOption;
	protected JPanel selectionPanel;

	// Gain and Bias Sliders
	protected JSlider gainFactorSlider;
	protected JSlider biasFactorSlider;

	// Zoom Slider
	protected JSlider zoomSlider;

	// Gain and Bias
	protected GainFilter adjustGain;
	protected BiasFilter adjustBias;
	protected JMenu gainMenu;
	protected JMenu biasMenu;
	protected double gainFactor;
	protected int biasFactor;
	protected JMenu optionsMenu;

	// BufferedImage Variables
	protected BufferedImage originalImage;
	protected BufferedImage finalImage;
	// Image Filter
	protected ImageFilter f = null;
	protected ImageRegionFilter f2 = null;

	// Image Panel
	protected JTabbedPane imagePane;
	protected ImagePanel originalPanel;

	/**
	 * Constructor Method for GUI
	 */
	public ImageProcessor() {
		// Exit on Close
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// sets up custom gain/bias filter objects
		adjustGain = new GainFilter();
		adjustBias = new BiasFilter();

		// Creates the Menus for file options, gain, and bias adjustments
		optionsMenu = new JMenu("File Options");
		gainMenu = new JMenu("Gain Adjustment");
		biasMenu = new JMenu("Bias Adjustment");

		// File Options Menu setup
		openImageOption = new JMenuItem("Open A New Image File");
		openImageOption.addActionListener(this);
		optionsMenu.add(openImageOption);

		// Add Seperator in file menu

		optionsMenu.addSeparator();

		saveImageOption = new JMenuItem("Save The Filtered Image");
		saveImageOption.addActionListener(this);
		saveImageOption.setEnabled(false);

		optionsMenu.add(saveImageOption);

		// Setup bias Slider
		biasFactorSlider = new JSlider(-200, 200);
		biasFactorSlider.setMajorTickSpacing(50);
		biasFactorSlider.setPaintTicks(true);
		biasFactorSlider.setPaintLabels(true);
		biasFactorSlider.addChangeListener(this);
		biasFactorSlider.setEnabled(false);
		biasFactorSlider.addChangeListener(this);
		biasMenu.add(biasFactorSlider);
		biasMenu.setEnabled(false);

		// Setup gain slider
		gainFactorSlider = new JSlider(2, 8);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(2, new JLabel("0.5"));
		labelTable.put(3, new JLabel("0.75"));
		labelTable.put(4, new JLabel("1.0"));
		labelTable.put(5, new JLabel("1.25"));
		labelTable.put(6, new JLabel("1.5"));
		labelTable.put(7, new JLabel("1.75"));
		labelTable.put(8, new JLabel("2.0"));
		gainFactorSlider.setLabelTable(labelTable);
		gainFactorSlider.setPaintLabels(true);
		gainFactorSlider.setEnabled(false);
		gainFactorSlider.addChangeListener(this);
		gainMenu.add(gainFactorSlider);
		gainMenu.setEnabled(false);

		// Setup zoom slider
		zoomSlider = new JSlider(0, 10);
		zoomSlider.setMajorTickSpacing(1);
		zoomSlider.setPaintTicks(true);
		zoomSlider.setPaintLabels(true);

		zoomSlider.addChangeListener(this);
		JMenu zoomMenu = new JMenu("Zoom");
		zoomMenu.add(zoomSlider);

		// Set up the menu bar, and add the items needed
		JMenuBar optionsBar = new JMenuBar();
		optionsBar.add(optionsMenu);
		optionsBar.add(biasMenu);
		optionsBar.add(gainMenu);
		optionsBar.add(zoomMenu);
		setJMenuBar(optionsBar);

		//// Add Radio Buttons for the filters
		button1 = new JRadioButton("Red-Green Swap", false);
		button2 = new JRadioButton("Red-Blue Swap", false);
		button3 = new JRadioButton("Green-Blue Swap", false);
		button4 = new JRadioButton("Black and White", false);
		button5 = new JRadioButton("Rotate clockwise", false);
		button6 = new JRadioButton("Rotate counter-clockwise", false);
		button7 = new JRadioButton("Gain", false);
		button8 = new JRadioButton("Bias", false);
		button9 = new JRadioButton("Blur", false);
		button10 = new JRadioButton("Red-Select", false);
		button11 = new JRadioButton("Green-Select", false);
		button12 = new JRadioButton("Blue-Select", false);

		// Set Tool-tips for each radio Button
		button1.setToolTipText("This Filter will swap green colors with red");
		button2.setToolTipText("This Filter will swap red colors with blue");
		button3.setToolTipText("This Filter will swap green colors with blue");
		button4.setToolTipText("This Filter will convert the image to Black and White");
		button5.setToolTipText("This Button will rotate the image clockwise");
		button6.setToolTipText("This Button will rotate the image counterclock-wise");
		button7.setToolTipText("This Filter can make the image  contrast brighter or darker, use slider to adjust");
		button8.setToolTipText(
				"This Filter will select a bais filter, you can select how bright or dark you want the image to be");
		button9.setToolTipText("This Filter will blur the image");
		button10.setToolTipText("This Filter will smooth out red areas of the image pallet");
		button11.setToolTipText("This FIlter will smooth out green areas of the image pallet");
		button12.setToolTipText("This Filter will smooth out blue areas of the image pallet");
		// Set tool-tips for each slider
		biasFactorSlider.setToolTipText(
				"Slide to adjust the amount of bias, and select Apply {Negative values will make image darker, positive values will make image brighter}");
		gainFactorSlider.setToolTipText(
				"Slide to adjust the amount of gain, and select Apply {Higher gain values will result in higher contrast, and brighter image}");

		// Create a button group to store the buttons so only one can be
		// selected
		filterGroup = new ButtonGroup();
		filterGroup.add(button1);
		filterGroup.add(button2);
		filterGroup.add(button3);
		filterGroup.add(button4);
		filterGroup.add(button5);
		filterGroup.add(button6);
		filterGroup.add(button7);
		filterGroup.add(button8);
		filterGroup.add(button9);
		filterGroup.add(button10);
		filterGroup.add(button11);
		filterGroup.add(button12);

		// Add a new panel to put into main panel
		radioButtons = new JPanel();
		radioButtons.setLayout(new GridLayout(4, 3));
		radioButtons.add(button1);
		radioButtons.add(button2);
		radioButtons.add(button3);
		radioButtons.add(button4);
		radioButtons.add(button5);
		radioButtons.add(button6);
		radioButtons.add(button7);
		radioButtons.add(button8);
		radioButtons.add(button9);
		radioButtons.add(button10);
		radioButtons.add(button11);
		radioButtons.add(button12);

		radioButtons
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Select an Option:"));
		// Initially Disable the Radio Buttons
		button1.setEnabled(false);
		button2.setEnabled(false);
		button3.setEnabled(false);
		button4.setEnabled(false);
		button5.setEnabled(false);
		button6.setEnabled(false);
		button7.setEnabled(false);
		button8.setEnabled(false);
		button9.setEnabled(false);
		button10.setEnabled(false);
		button11.setEnabled(false);
		button12.setEnabled(false);

		// Setup Multi-selection radio buttons
		On = new JRadioButton("On");
		Off = new JRadioButton("Off");
		multiSelect = new ButtonGroup();
		multiSelect.add(On);
		multiSelect.add(Off);

		On.setEnabled(false);
		Off.setEnabled(false);
		multiSelectPanel = new JPanel();
		multiSelectPanel.setLayout(new GridLayout(1, 3));
		multiSelectPanel.add(On);
		multiSelectPanel.add(Off);
		multiSelectPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Multi-Filter Select"));
		multiSelectPanel.setPreferredSize(new Dimension(150, 80));

		// Sets up the Tabbed Pane for the image

		imagePane = new JTabbedPane();

		// Create a button to apply filters, and to apply adjustments
		filterButton = new JButton("Apply");
		filterButton.setEnabled(false);
		filterButton.setToolTipText("Apply the Selected Operation");

		// Setup Crop Button
		cropButton = new JButton("Crop");
		cropButton.setEnabled(false);
		cropButton.setToolTipText("Select Region of Image to Crop");

		// Set up region selection button
		regionFilterButton = new JButton("Region Filter");
		regionFilterButton.setEnabled(false);
		regionFilterButton.setToolTipText(
				"Select this button to apply filter to a region of Image, will not work for Rotation, blur,  gain, and bias");

		// Setup panel for Apply, crop, and region-select buttons
		selectionPanel = new JPanel();
		selectionPanel.setLayout(new GridLayout(1, 2));
		selectionPanel.setPreferredSize(new Dimension(350, 30));
		selectionPanel.add(filterButton);
		selectionPanel.add(cropButton);
		selectionPanel.add(regionFilterButton);

		// Set the JPanel area
		JPanel content = new JPanel();
		content.setLayout(new FlowLayout());
		content.setPreferredSize(new Dimension(1024, 900));
		content.add(radioButtons);
		content.add(multiSelectPanel);

		content.add(selectionPanel);
		content.add(imagePane);
		setTitle("ImageProcessor");
		setContentPane(content);
		pack();

		// All major events for standard filters will occur when the "apply"
		// button is pressed

		filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				On.setEnabled(true);
				Off.setEnabled(true);
				gainMenu.setEnabled(false);
				biasMenu.setEnabled(false);
				gainFactorSlider.setEnabled(false);
				biasFactorSlider.setEnabled(false);

				// Setup conditions depending on which button is pressed

				if (button1.isSelected()) {

					f = new RedGreenSwapFilter();

				} else if (button2.isSelected()) {

					f = new RedBlueSwapFilter();

				} else if (button3.isSelected()) {

					f = new GreenBlueSwapFilter();

				} else if (button4.isSelected()) {

					f = new BlackAndWhiteFilter();

				} else if (button5.isSelected()) {

					f = new RotateClockwise();

				} else if (button6.isSelected()) {

					f = new RotateCounterClockwise();

				} else if (button7.isSelected()) {
					// conditions to apply gain filter
					gainMenu.setEnabled(true);
					gainFactorSlider.setEnabled(true);

					adjustGain.setGainValue(gainFactor);
					finalImage = adjustGain.filter(originalImage);

					if (imagePane.getTabCount() >= 2) {
						imagePane.setComponentAt(1, new ImagePanel(finalImage));
					} else {
						imagePane.add(new ImagePanel(finalImage), "Filtered");
					}

				} else if (button8.isSelected()) {
					// conditions to apply bias filter
					biasMenu.setEnabled(true);
					biasFactorSlider.setEnabled(true);

					adjustBias.setBiasValue(biasFactor);
					finalImage = adjustBias.filter(originalImage);
					if (imagePane.getTabCount() >= 2) {
						imagePane.setComponentAt(1, new ImagePanel(finalImage));
					} else {
						imagePane.add(new ImagePanel(finalImage), "Filtered");
					}

				} else if (button9.isSelected()) {

					f = new BlurFilter();

				} else if (button10.isSelected()) {

					f = new RedSelectFilter();

				} else if (button11.isSelected()) {

					f = new GreenSelectFilter();

				} else {

					f = new BlueSelectFilter();
				}
				// Determines is multi filter apply selection option button is
				// on or off
				if (!(button7.isSelected() || button8.isSelected())) {

					if ((!On.isSelected()) || (Off.isSelected())) {

						finalImage = f.filter(originalImage);

						if (imagePane.getTabCount() >= 2) {
							imagePane.setComponentAt(1, new ImagePanel(finalImage));
						} else {
							imagePane.add(new ImagePanel(finalImage), "Filtered");
						}

					} else {
						finalImage = f.filter(finalImage);

						if (imagePane.getTabCount() >= 2) {
							imagePane.setComponentAt(1, new ImagePanel(finalImage));
						} else {
							imagePane.add(new ImagePanel(finalImage), "Filtered");
						}
					}

				}

				saveImageOption.setEnabled(true);

			}
		});

		// Crop Button action Listener

		cropButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// make buttons visable
				On.setEnabled(true);
				Off.setEnabled(true);
				// if multi select option is turned on, then cumilitive effects
				// will occur with crop
				if ((!On.isSelected()) || (Off.isSelected())) {
					try {
						f2 = new CropImageFilter();
						f2.setRegion(originalPanel.getSelectedRegion());
						finalImage = f2.filter(originalImage);
						if (imagePane.getTabCount() >= 2) {
							imagePane.setComponentAt(1, new ImagePanel(finalImage));
						} else {
							imagePane.add(new ImagePanel(finalImage), "Filtered");
						}
					} catch (Exception d) {

						return;

					}
				} else {
					try {
						f2 = new CropImageFilter();
						f2.setRegion(originalPanel.getSelectedRegion());
						finalImage = f2.filter(finalImage);
						if (imagePane.getTabCount() >= 2) {
							imagePane.setComponentAt(1, new ImagePanel(finalImage));
						} else {
							imagePane.add(new ImagePanel(finalImage), "Filtered");
						}
					} catch (Exception d) {

						return;

					}

				}

			}

		});

		// Region Filter Button Listener
		regionFilterButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				On.setEnabled(true);
				Off.setEnabled(true);
				// sets up the filtering
				if (button1.isSelected()) {
					f2 = new RedGreenSwapSelect();
				} else if (button2.isSelected()) {
					f2 = new RedBlueSwapSelect();
				} else if (button3.isSelected()) {
					f2 = new GreenBlueSwapSelect();
				} else if (button4.isSelected()) {
					f2 = new BlackAndWhiteSelect();
				} else if (button9.isSelected()) {
					f2 = new BlurFilterSelect();

				} else if (button10.isSelected()) {
					f2 = new RedSelectFilterSelect();
				} else if (button11.isSelected()) {
					f2 = new GreenSelectFilterSelect();
				} else if (button12.isSelected()) {
					f2 = new BlueSelectFilterSelect();
				}
				// if statement will determine is multi selection option is
				// turned on for cumulative selection,
				// if on, it will accumulate the filters.
				if ((!On.isSelected()) || (Off.isSelected())) {
					try {
						f2.setRegion(originalPanel.getSelectedRegion());
						finalImage = f2.filter(originalImage);
						if (imagePane.getTabCount() >= 2) {
							imagePane.setComponentAt(1, new ImagePanel(finalImage));
						} else {
							imagePane.add(new ImagePanel(finalImage), "Filtered");
						}
					} catch (Exception d) {
						return;
					}
				} else {
					try {
						f2.setRegion(originalPanel.getSelectedRegion());
						finalImage = f2.filter(finalImage);
						if (imagePane.getTabCount() >= 2) {
							imagePane.setComponentAt(1, new ImagePanel(finalImage));
						} else {
							imagePane.add(new ImagePanel(finalImage), "Filtered");
						}
					} catch (Exception d) {
						return;
					}

				}

			}

		});

	}

	/**
	 * Action performed method that will dictate what will happen when menu
	 * items to open or save a file are selected.
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == openImageOption) {

			// Create a JFileChooser Dialog Box to select image files

			JFileChooser selectFile = new JFileChooser();

			// Select for certain file types

			FileNameExtensionFilter filterFiles = new FileNameExtensionFilter("JPG, JPEG, GIF, PNG, & BMP Images",
					"jpg", "jpeg", "gif", "png", "bmp");

			selectFile.setFileFilter(filterFiles);

			int returnVal = selectFile.showOpenDialog(selectFile);

			// If statements for selection approval

			if (returnVal == JFileChooser.APPROVE_OPTION) {

				File imageFile = selectFile.getSelectedFile();

				try {

					originalImage = ImageIO.read(imageFile);
					imagePane.removeAll();
					// imagePane.add(new ImagePanel(originalImage), "Original
					// Image");
					originalPanel = new ImagePanel(originalImage);
					imagePane.add("Original Image", originalPanel);

					// Enable the Radio Buttons to allow for filter selection
					button1.setEnabled(true);
					button2.setEnabled(true);
					button3.setEnabled(true);
					button4.setEnabled(true);
					button5.setEnabled(true);
					button6.setEnabled(true);
					button7.setEnabled(true);
					button8.setEnabled(true);
					button9.setEnabled(true);
					button10.setEnabled(true);
					button11.setEnabled(true);
					button12.setEnabled(true);
					cropButton.setEnabled(true);
					filterButton.setEnabled(true);
					regionFilterButton.setEnabled(true);

				} catch (Exception f) {
					return;
				}

			} else {
				return;
			}

		} else if (e.getSource() == saveImageOption) {
			// Save the File
			JFileChooser writeImage = new JFileChooser();
			FileNameExtensionFilter filteredFiles = new FileNameExtensionFilter("JPG", "jpg");
			writeImage.setFileFilter(filteredFiles);

			int returnValue = writeImage.showSaveDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				try {
					File file = writeImage.getSelectedFile();
					String fileName = file.getAbsolutePath();
					file = new File(fileName + ".jpg");
					ImageIO.write(finalImage, "JPG", file);

				} catch (Exception g) {

					return;

				}

			} else {
				return;
			}

		}

	}

	/**
	 * This state changed method will determine what value will be passed in for
	 * "X" variable in the gain/bias filters when the slider is used
	 */
	public void stateChanged(ChangeEvent e) {

		JSlider src = (JSlider) e.getSource();

		if (src == gainFactorSlider) {
			if (!src.getValueIsAdjusting()) {
				int val = (int) src.getValue();
				gainFactor = val * 0.25;

			}
		} else if (src == biasFactorSlider) {
			if (!src.getValueIsAdjusting()) {
				biasFactor = (int) src.getValue();

			}
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
