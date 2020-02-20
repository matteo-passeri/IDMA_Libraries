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
	
//	public IDMA_Game(int width, int height) {
//		this.width = width;
//		this.height = height;	
//		this.title = title;
//		keyManager = new IDMA_KeyManager();
//		mouseManager = new IDMA_MouseManager();
////		new IDMA_Assets();
//	}
	
//	private void init() {
//		window = new IDMA_Window(width, height);
//		window.getFrame().addKeyListener(keyManager);
//		// we need both Listener for Frame and Canvas
//		// depending on which is on focus
//		window.getFrame().addMouseListener(mouseManager);
//		window.getFrame().addMouseMotionListener(mouseManager);
//		window.getCanvas().addMouseListener(mouseManager);
//		window.getCanvas().addMouseMotionListener(mouseManager);
//		Assets.init();
//		
//		
//		handler = new IDMA_Handler(this);
//		gameCamera = new IDMA_GameCamera(handler, 0, 0);
//		
//		
//		gameState = new IDMA_GameState(handler);
//		menuState = new IDMA_MenuState(handler);
//		IDMA_State.setState(menuState);
//		
////		ball = imageLoader.loadImage("/texture/bouncingBall.png");
////		sheet = new spriteSheet(ball);
//	}
	
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
	
	
	
//	public IDMA_GameCamera getGameCamera() {
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
///**
// * This class is the barebones blueprint for a game in Java. It includes the
// * render-loop, frames/updates per second information, keyboard/mouse input, the
// * screen, and other information.
// *
// * To start, extend StandardGame, and call this.StartGame();
// *
// * To stop the game, call this.StopGame();
// *
// * To add listeners to your project, call
// * 'super.addMouseListener/MouseMotionListener
// * /addKeyListener/MouseWheelListener, etc. Any listeners that java.awt.Canvas
// * supports are supported by StandardGame.
// */
//public abstract class IDMA_Game extends Canvas implements Runnable {
//
//	private static final long serialVersionUID = 1L;
//
//	// Default screen size
//	public static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
//
//	// Window for the game
//	private IDMA_Window window;
//
//	// handler
//	private IDMA_Handler handler;
//
//	// Input devices
//	private IDMA_KeyManager keyboard;
//	private IDMA_MouseManager mouse;
//
//	// Game loop thread
//	private Thread thread;
//
//	// Debugging variables
//	private int currentFPS;
//	private boolean running;
//	private boolean consoleFPS;
//	private boolean titleFPS;
//	private static BufferStrategy bufferStrategy = null;
//
//	/**
//	 * Creates a StandardGame object with size width x height, and title.
//	 *
//	 *
//	 * @param int width, int height, String title
//	 */
//	public IDMA_Game(int width, int height, String title) {
//		this.thread = null;
//		this.running = false;
//		this.currentFPS = 0;
//		this.consoleFPS = true;
//		this.titleFPS = true;
//		this.window = new IDMA_Window(width, height, title, this);
//
//		this.createBufferStrategy(3);
//
//		IDMA_Game.bufferStrategy = this.getBufferStrategy();
//
//		this.mouse = new IDMA_MouseManager();
//		this.keyboard = new IDMA_KeyManager();
//
//		this.addMouseListener(this.mouse);
//		this.addMouseMotionListener(this.mouse);
//		this.addKeyListener(this.keyboard);
//	}
//
//	/**
//	 * Creates a StandardGame object with aspect ration 16:9 via the supplied width.
//	 *
//	 *
//	 * @param int width, String title
//	 */
//	public IDMA_Game(int width, String title) {
//		this.window = null;
//		this.thread = null;
//		this.running = false;
//		this.currentFPS = 0;
//		this.consoleFPS = true;
//		this.titleFPS = true;
//		this.window = new IDMA_Window(width, (width / 16 * 9), title, this);
//
//		this.createBufferStrategy(3);
//
//		IDMA_Game.bufferStrategy = this.getBufferStrategy();
//
//		this.mouse = new IDMA_MouseManager();
//		this.keyboard = new IDMA_KeyManager();
//
//		this.addMouseListener(this.mouse);
//		this.addMouseMotionListener(this.mouse);
//		this.addKeyListener(this.keyboard);
//	}
//
//	/**
//	 * Generates a StandardGame object with a title, and forces the screen size to
//	 * whatever the user's monitor dimensions are.
//	 *
//	 *
//	 * @param String title
//	 */
//	public IDMA_Game(String title) {
//		this.thread = null;
//		this.running = false;
//		this.currentFPS = 0;
//		this.consoleFPS = true;
//		this.titleFPS = true;
//		this.window = new IDMA_Window(handler.getScreenWidth(), handler.getScreenHeight(), title, this);
//		this.createBufferStrategy(3);
//
//		IDMA_Game.bufferStrategy = this.getBufferStrategy();
//
//		this.mouse = new IDMA_MouseManager();
//		this.keyboard = new IDMA_KeyManager();
//
//		this.addMouseListener(this.mouse);
//		this.addMouseMotionListener(this.mouse);
//		this.addKeyListener(this.keyboard);
//	}
//
//	/**
//	 * Initializes the thread and starts the game loop.
//	 */
//	public void startGame() {
//		if (this.running) {
//			return;
//		}
//		this.thread = new Thread(this);
//		this.thread.start();
//		this.running = true;
//	}
//
//	/**
//	 * Halts the thread, stops the game.
//	 */
//	public void stopGame() {
//		if (!this.running) {
//			System.out.println("Not running");
//			return;
//		}
//		// Closes window
//		this.window.getFrame().dispose();
//
//		// Closes the thread
//		this.thread.interrupt();
//
//		// Flags the game as no longer running
//		this.running = false;
//	}
//
//	/**
//	 * The game loop.
//	 */
//	@Override
//	public void run() {
//		requestFocus();
//		long lastTime = System.nanoTime();
//		double ns = 1.6666666666666666E7D;
//		double delta = 0.0D;
//		long timer = System.currentTimeMillis();
//		int frames = 0;
//		int updates = 0;
//		while (this.running) {
//			boolean renderable = false;
//
//			long now = System.nanoTime();
//			delta += (now - lastTime) / ns;
//			lastTime = now;
//
//			while (delta >= 1.0D) {
//				IDMA_Command.update((float) delta);
//
//				this.tick();
//
//				delta--;
//				updates++;
//
//				renderable = true;
//			}
//
////			if (renderable) {
////				frames++;
////				IDMA_Game.bufferStrategy = getBufferStrategy();
////				IDMA_Draw.Renderer = (Graphics2D) IDMA_Game.bufferStrategy.getDrawGraphics();
////
////				IDMA_Draw.Renderer.setColor(Color.BLACK);
////				IDMA_Draw.Renderer.fillRect(0, 0, this.getGameWidth(), this.getGameHeight());
////
////				this.render();
////
////				IDMA_Draw.Renderer.dispose();
////				IDMA_Game.bufferStrategy.show();
////			}
//
//			if (System.currentTimeMillis() - timer > 1000L) {
//				timer += 1000L;
//
//				this.currentFPS = frames;
//
//				if (this.titleFPS) {
//					this.window.setTitle(
//							String.valueOf(this.window.getTitle()) + " | " + updates + " ups, " + frames + " fps");
//				}
//				if (this.consoleFPS) {
//					System.out.println(
//							String.valueOf(this.window.getTitle()) + " | " + updates + " ups, " + frames + " fps");
//				}
//
//				updates = 0;
//				frames = 0;
//			}
//		}
//
//		this.stopGame();
//	}
//
//	/**
//	 * Handles all physics/game state/object state updates.
//	 */
//	public abstract void tick();
//
//	/**
//	 * Renders graphics, text, sprites, etc. to the IDMA_Window.
//	 */
//	public abstract void render();
//
//	public IDMA_Game getGame() {
//		return this;
//	}
//
//	public int getFPS() {
//		return this.currentFPS;
//	}
//
////	public int getGameWidth() {
////		return this.window.getWidth();
////	}
////
////	public int getGameHeight() {
////		return this.window.getHeight();
////	}
//
//	public void printFramesToConsole(boolean print) {
//		this.consoleFPS = print;
//	}
//
//	public void printFramesToTitle(boolean print) {
//		this.titleFPS = print;
//	}
//
//	public IDMA_Window getWindow() {
//		return this.window;
//	}
//
//	public IDMA_KeyManager getKeyManager() {
//		return this.keyboard;
//	}
//
//	public void setKeyboard(IDMA_KeyManager keyboard) {
//		this.keyboard = keyboard;
//	}
//
//	public IDMA_MouseManager getMouseManager() {
//		return this.mouse;
//	}
//
//	public void setMouse(IDMA_MouseManager mouse) {
//		this.mouse = mouse;
//	}
//
////	private int getScreenWidth() {
////		return (int) IDMA_Game.SCREEN_DIMENSION.getWidth();
////	}
////
////	public int getScreenHeight() {
////		return (int) IDMA_Game.SCREEN_DIMENSION.getHeight();
////	}
////	private int getScreenWidth() {
////		return (int) IDMA_Game.SCREEN_DIMENSION.getWidth();
////	}
////
////	public int getScreenHeight() {
////		return (int) IDMA_Game.SCREEN_DIMENSION.getHeight();
////	}
//
//}
