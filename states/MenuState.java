package IDMA_Libraries_Alpha.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import IDMA_Libraries_Alpha.UI.ClickListener;
import IDMA_Libraries_Alpha.UI.UIImageButton;
import IDMA_Libraries_Alpha.UI.UIManager;
import IDMA_Libraries_Alpha.utils.Handler;
import IDMA_Libraries_Alpha.utils.ImageLoader;
import IDMA_Libraries_Alpha.utils.SpriteSheet;

public class MenuState extends State {
	
	private UIManager uiManager;
	
	// menu buttons
	public static BufferedImage[] btn_start;
	
	private static final int width = 64, height = 64;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		// menu buttons
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/texture/sheet.png"));

		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width *8, height *4, width*2, height);
		btn_start[1] = sheet.crop(width *8, height *5, width*2, height);
		
		uiManager.addObject(new UIImageButton(200, 200, 128, 64, btn_start, new ClickListener() {

			@Override
			public void onClick() {
				// UIManager has to be disable or 
				// user can still click the buttons
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}
		}));
	}
	
	@Override
	public void tick() {
		uiManager.tick();

		// temporally just for dev (skip menu)
		handler.getMouseManager().setUIManager(null);
		State.setState(handler.getGame().gameState);
		// ...
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}
