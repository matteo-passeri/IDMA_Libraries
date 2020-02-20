package IDMA_Libraries_Alpha;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;

/**
 * This class is the class that instantiates the Screen. It takes four
 * parameters: width, height, a title, and the Core class itself that will
 * automatically be added to the FRAME as a component due to it extending class
 * once it is instantiated.
 */
@SuppressWarnings("serial")
public class Window extends Canvas {

//	private final String ICON = "res/img/IDMA_Icon.png";
	private final JFrame FRAME;
	private final String TITLE;

	private int width;
	private int height;

	
	public Window(int width, int height, String title, Object core) {
		this.FRAME = new JFrame();

		this.FRAME.setTitle(title);
		this.TITLE = title;

		// Sets dimensions
		this.width = width;
		this.height = height;

		// Sets frame information
		this.FRAME.setMinimumSize(new Dimension(width, height));
		this.FRAME.setMaximumSize(new Dimension(width, height));
		this.FRAME.setPreferredSize(new Dimension(width, height));
		this.FRAME.getContentPane().setSize(new Dimension(width, height));
//		this.FRAME.setIconImage(IDMA_ImageLoader.loadImage(this.ICON));
		this.FRAME.setResizable(false);
		this.FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.FRAME.setLocationRelativeTo(null);

		this.FRAME.add((Component) core);
		this.FRAME.pack();

		this.FRAME.setVisible(true);

	}

	public void setBackgroundColor(Color color) {
		this.FRAME.setBackground(color);
	}

	public JFrame getFrame() {
		return FRAME;
	}

	// set & get the width of the frame.
	public int getWidth() {
		return width;
	}

	public void setWidth(short width) {
		this.width = width;
	}

	// set & get the provided height of the frame.
	public int getHeight() {
		return height;
	}

	public void setHeight(short height) {
		this.height = height;
	}

	// set & get the Title
	public String getTitle() {
		return TITLE;
	}

	public void setTitle(String title) {
		this.FRAME.setTitle(title);
	}

}
