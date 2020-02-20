package IDMA_Libraries_Alpha;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;

import IDMA_Libraries_Alpha.input.KeyManager;
import IDMA_Libraries_Alpha.input.MouseManager;
import IDMA_Libraries_Alpha.utils.Handler;

/**
 * This class is the blueprint for a program in Java.
 */
public abstract class Program extends Canvas {

	private static final long serialVersionUID = 1L;

	// Default screen size
	public static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();

	// Input devices
	private KeyManager keyboard;
	private MouseManager mouse;

	// handler
	private Handler handler;

	// Window for the game
	private Window window;

	/**
	 * Creates a Core object with size width x height, and title.
	 *
	 *
	 * @param int width, int height, String title
	 */
	public Program(int width, int height, String title) {
		this.window = new Window(width, height, title, this);
	}

	/**
	 * Creates a Game object with aspect ration 16:9 via the supplied width.
	 *
	 *
	 * @param int width, String title
	 */
	public Program(int width, String title) {
		this.window = new Window(width, (width / 16 * 9), title, this);
	}

	/**
	 * Generates a Game object with a title, and forces the screen size to
	 * whatever the user's monitor dimensions are.
	 *
	 *
	 * @param String title
	 */
	public Program(String title) {
		this.window = new Window(handler.getScreenWidth(), handler.getScreenHeight(), title, this);
	}

//	public int width() {
//		return this.window.getWidth();
//	}
//
//	public int height() {
//		return this.window.getHeight();
//	}

	public Window getWindow() {
		return this.window;
	}

	public KeyManager getKeyManager() {
		return this.keyboard;
	}

	public void setKeyboard(KeyManager keyboard) {
		this.keyboard = keyboard;
	}

	public MouseManager getMouseManager() {
		return this.mouse;
	}

	public void setMouse(MouseManager mouse) {
		this.mouse = mouse;
	}

//	private int getScreenWidth() {
//		return (int) Program.SCREEN_DIMENSION.getWidth();
//	}
//
//	public int getScreenHeight() {
//		return (int) Program.SCREEN_DIMENSION.getHeight();
//	}

}
