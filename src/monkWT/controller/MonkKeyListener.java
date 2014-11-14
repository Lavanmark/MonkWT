package monkWT.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import monkWT.model.Model;


public class MonkKeyListener implements KeyListener{

	private Model mainModel;
	
	public MonkKeyListener(Model m){
		mainModel = m;
	}
	
	private void gameKeyPressed(int key){
		switch(key){
		case KeyEvent.VK_W:
			//UP
			break;
		case KeyEvent.VK_S:
			//DOWN
			break;
		case KeyEvent.VK_A:
			//LEFT

			break;
		case KeyEvent.VK_D:
			//RIGHT
			break;
		case KeyEvent.VK_SPACE:
			//JUMP
			
			break;
		case KeyEvent.VK_H:
			//ACTION 1 (punch or hit with object in hand)
			break;
		case KeyEvent.VK_J:
			//ACTION 2
			break;
		case KeyEvent.VK_K:
			//ACTION 3 (kick)
			break;
		case KeyEvent.VK_L:
			//ACTION 4
			break;
			
		}
	}
	
	private void gameKeyReleased(int key){
		switch(key){
		case KeyEvent.VK_W:
			//UP
			break;
		case KeyEvent.VK_S:
			//DOWN
			break;
		case KeyEvent.VK_A:
			//LEFT
			System.out.println("stop left");
			break;
		case KeyEvent.VK_D:
			//RIGHT
			break;
		case KeyEvent.VK_SPACE:
			//JUMP
			
			System.out.println("jump");
			break;
		case KeyEvent.VK_H:
			//ACTION 1
			break;
		case KeyEvent.VK_J:
			//ACTION 2
			break;
		case KeyEvent.VK_K:
			//ACTION 3
			break;
		case KeyEvent.VK_L:
			//ACTION 4
			break;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		gameKeyPressed(ke.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		gameKeyReleased(ke.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		
	}

}
