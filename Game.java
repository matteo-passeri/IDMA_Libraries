package IDMA_Libraries_Alpha;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import IDMA_Libraries_Alpha.input.KeyManager;
import IDMA_Libraries_Alpha.input.MouseManager;
import IDMA_Libraries_Alpha.states.State;
import IDMA_Libraries_Alpha.utils.Handler;


public abstract class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
		
	private Window window;	
	
	private int width, height;
	public String title;
	
	private boolean running = false;	
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private boolean titleFPS;
	
	// states
	public State gameState;
	public State menuState;
	
	// input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	// Camera
//	private GameCamera gameCamera;
	
	// Handler
	private Handler handler;
	
	/**
	 * Creates a Core object with size width x height, and title.
	 *
	 *
	 * @param int width, int height, String title
	 */
	public Game(int width, int height, String title) {
		this.window = new Window(width, height, title, this);
		this.titleFPS = true;

		this.mouseManager = new MouseManager();
		this.keyManager = new KeyManager();
		this.addMouseListener(this.mouseManager);
		this.addMouseMotionListener(this.mouseManager);
		this.addKeyListener(this.keyManager);
//		this.add(game);
		
		start();
	}

	/**
	 * Creates a Core object with aspect ration 16:9 via the supplied width.
	 *
	 * @param int width, String title
	 */
	public Game(int width, String title) {
		this.window = new Window(width, (width / 16 * 9), title, this);
		this.titleFPS = true;
		
		this.mouseManager = new MouseManager();
		this.keyManager = new KeyManager();
		this.addMouseListener(this.mouseManager);
		this.addMouseMotionListener(this.mouseManager);
		this.addKeyListener(this.keyManager);
		
		start();
	}

	/**
	 * Generates a Core object with a title in fullscreen.
	 *
	 * @param String title
	 */
	public Game(String title) {
		this.window = new Window(getScreenWidth(), getScreenHeight(), title, this);
		this.titleFPS = true;

		this.mouseManager = new MouseManager();
		this.keyManager = new KeyManager();
		this.addMouseListener(this.mouseManager);
		this.addMouseMotionListener(this.mouseManager);
		this.addKeyListener(this.keyManager);
		
		start();
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
		return this.keyManager;
	}

	public void setKeyboard(KeyManager keyboard) {
		this.keyManager = keyboard;
	}

	public MouseManager getMouseManager() {
		return this.mouseManager;
	}	

	public void setMouse(MouseManager mouse) {
		this.mouseManager = mouse;
	}

	private int getScreenWidth() {
		return (int) Core.SCREEN_DIMENSION.getWidth();
	}

	public int getScreenHeight() {
		return (int) Core.SCREEN_DIMENSION.getHeight();
	}
		
	// update variable
	private void tick() {
		keyManager.tick();
		
		if(State.getState() != null) 
			State.getState().tick();
		
	}
	
	// render
	private void render() {
		bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// clear first
		g.clearRect(0, 0, width, height);
		// draw start
		
		if(State.getState() != null) 
			State.getState().render(g);
		
		
		// draw stop
		bs.show();
		g.dispose();
	}
	

	@Override
	public void run() {
		
//		init();		
		
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		// checking FPS...
		long timer = 0;
		int ticks = 0;
		//...
		
		
		while (running) {
			 now = System.nanoTime();
			 delta += (now - lastTime) / timePerTick;
			// checking FPS...
			 timer += now - lastTime;
			 // ...
			 lastTime = now;
			 
			if( delta >= 1) {
			tick();
			render();
			// checking FPS...
			ticks++;
			// ...			
			delta--;
			}
			
			// checking FPS...
			if(timer >= 1000000000) {
//				System.out.println("Ticks and Frames: " + ticks + " per Second.");
				
				if (this.titleFPS) {
					this.window.setTitle(
							String.valueOf(this.window.getTitle()) + " | " + ticks + " fps");
				}
				ticks = 0;
				timer = 0;
			}
			
			
		}
		
		stop();
		
	}
	
	
	
//	public GameCamera getGameCamera() {
//		return gameCamera;
//	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public synchronized void start() {
		 if(running)
				return;
		 
		 running = true;		
		 thread = new Thread(this);
		 thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
