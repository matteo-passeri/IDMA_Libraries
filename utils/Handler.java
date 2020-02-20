package IDMA_Libraries_Alpha.utils;

import IDMA_Libraries_Alpha.Core;
import IDMA_Libraries_Alpha.Game;
import IDMA_Libraries_Alpha.Program;
import IDMA_Libraries_Alpha.Window;
import IDMA_Libraries_Alpha.input.KeyManager;
import IDMA_Libraries_Alpha.input.MouseManager;

public class Handler {

	private Core core;
	private Game game;
	private Program program;

	// Core
	public Handler(Core core) {
		this.core = core;
	}

	// GAME
	public Handler(Game core) {
		this.game = core;
	}

	// Program
	public Handler(Program core) {
		this.program = core;
	}

	// keyboard and mouse manager
	public KeyManager getKeyManager() {
		return core.getKeyManager();

	}

	public MouseManager getMouseManager() {
		return core.getMouseManager();

	}

	// get Width or Height of the WINDOW
	public int getGameWidth() {
		return core.getWindow().getWidth();
	}

	public int getGameHeight() {
		return core.getWindow().getHeight();
	}

	// get Width or Height
	public int getWidth() {
		return core.getWidth();
	}

	public int getHeight() {
		return core.getHeight();
	}

	// get ScreenWidth or ScreenHeight
	public int getScreenWidth() {
		return (int) Core.SCREEN_DIMENSION.getWidth();
	}

	public int getScreenHeight() {
		return (int) Core.SCREEN_DIMENSION.getHeight();
	}

	// get the Window
	public Window getWindow() {
		return this.getWindow();
	}

	// get the Game
	public Game getGame() {
		return game;
	}

	// get the Program
	public Program getProgram() {
		return program;
	}

}
