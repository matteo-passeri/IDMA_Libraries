package IDMA_Libraries_Alpha.states;

import java.awt.Graphics;

import IDMA_Libraries_Alpha.utils.Handler;

public class GameState extends State{
	
//	private World world;
	
//	private IDMA_Handler handler;
	
	public GameState(Handler handler) {
		super(handler);
//		world = new World(handler, "res/worlds/world1.txt");
//		handler.setWorld(world);
		
	}

	@Override
	public void tick() {
//		world.tick();
	}

	@Override
	public void render(Graphics g) {
//		world.render(g);
	}
	
}
