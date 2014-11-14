package monkWT.view;

import java.awt.Graphics;
import java.awt.Image;

import resources.ResourceLoader;

public class Menu {

	private ResourceLoader rl = new ResourceLoader();
	
	private Image menuTitle = rl.getTitle("menuTitle.png");
	private Image menuContinue = rl.getTitle("menuContinue.png"); 
	private Image menuMiniDoorC = rl.getTitle("menuMiniDoorC.png");
	private Image menuMiniDoorO = rl.getTitle("menuMiniDoorO.png");
	private Image menuLevelSelect = rl.getTitle("menuLevelSelect.png"); 
	private Image menuStore = rl.getTitle("menuStore.png");
	private Image menuQuit = rl.getTitle("menuQuit.png"); 
	private Image gameOver = rl.getTitle("gameOver.png");
	private Image loadImg = rl.getTitle("loading.png");
	
	public int hover = 0;
	public int section = 1;
	
	
	
	public void drawMain(Graphics g){
		
		if(section == 1){
			g.drawImage(menuTitle, 0, 0, null);
			g.drawImage(menuContinue, 500, 150, null);
			g.drawImage(menuLevelSelect, 500, 200, null);
			g.drawImage(menuStore, 500, 250, null);
			g.drawImage(menuQuit, 500, 300, null);
			if(hover != 1){
			g.drawImage(menuMiniDoorC, 500, 150, null);
			}else if(hover == 1){
				g.drawImage(menuMiniDoorO, 500, 150, null);
			}
			if(hover != 2){
				g.drawImage(menuMiniDoorC, 500, 200, null);
			}else if(hover == 2){
				g.drawImage(menuMiniDoorO, 500, 200, null);
			}
			if(hover != 3){
				g.drawImage(menuMiniDoorC, 500, 250, null);
			}else if(hover == 3){
				g.drawImage(menuMiniDoorO, 500, 250, null);
			}
			if(hover != 4){
				g.drawImage(menuMiniDoorC, 500, 300, null);
			}else if(hover == 4){
				g.drawImage(menuMiniDoorO, 500, 300, null);
			}
			
		}
		if(section == 2){
			
			
			
			
		}
		if(section == 3){
			g.drawImage(gameOver, 0, 0, null);
		}

	
		
	}
	public void drawLoad(Graphics g){
		
   	 	g.drawImage(loadImg, 50, 250, null);
	}
}
