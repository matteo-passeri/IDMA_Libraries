package IDMA_Libraries_Alpha.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
	public boolean attacking = false;
//	public boolean jumping = false;

	
	private boolean[] keys, justPressed, cantPress;
	public boolean up, down, right, left, jump;
	public boolean aUp, aDown, aRight, aLeft;
	
	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
		
	}
	
	public boolean keyJustPressed(int keyCode){
		  if(keyCode < 0 || keyCode >= keys.length)
		  return false;
		  return justPressed[keyCode];
		  }
	
	public void tick() {
		for (int i = 0; i < keys.length; i++) {
			if(cantPress[i] && !keys[i]){
				cantPress[i] = false;				
			} else if(justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if(!cantPress[i] && keys[i]) {
				justPressed[i] = true;
			}
		}
				
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		jump = keys[KeyEvent.VK_SPACE];
		
		// attack buttons
		aUp = keys[KeyEvent.VK_UP];
		aDown = keys[KeyEvent.VK_DOWN];
		aLeft = keys[KeyEvent.VK_LEFT];
		aRight = keys[KeyEvent.VK_RIGHT];
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
		
		// pushing attack buttons
		if(keys[e.getKeyCode()] == aUp
				|| keys[e.getKeyCode()] == aDown 
				|| keys[e.getKeyCode()] == aLeft 
				|| keys[e.getKeyCode()] == aRight)
			attacking = true;
		
//		// jump
//		if(keys[e.getKeyCode()] == jump && !jumping)
//			jumping = true;
		
//		// crouch
//		if(keys[e.getKeyCode()] == down)
//			crouch = true;
		
		
//		System.out.println("Attack in keyPressed is: " + attacking);
//		System.out.println("Jump in keyPressed is: " + jump);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
		if(attacking == true)
			attacking = false;
		
//		if(jumping == true)
//			jumping = false;
		
//		System.out.println("Attack in keyRelease is: " + attacking);
//		System.out.println("And jump in keyRelease is: " + jump);

	}

	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
