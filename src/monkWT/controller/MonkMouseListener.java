package monkWT.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import monkWT.model.Model;
import monkWT.model.Model.State;
import monkWT.view.Menu;

public class MonkMouseListener implements MouseListener, MouseMotionListener{

	private Model mainModel;
	private Menu viewMenu;
	
	public MonkMouseListener(Model m){
		mainModel = m;
	}
	public void gameShutdown() {
		if(mainModel.getState() == State.DESTROYED){
			//save game?
			System.exit(0);
		}
	}
	public void setMenu(Menu m){
		viewMenu = m;
	}
	
	
	public void mouseMoved(MouseEvent me){
    	
    	int x = me.getX();
		int y = me.getY();
		if(mainModel.getState() == State.MENU && viewMenu.section == 1){
			viewMenu.hover = 0;
			if(x >= 500){
				viewMenu.hover = 0;
				if( y >= 150 && y<200){
					viewMenu.hover = 1;
				}
				if(y >= 200 && y<250 ){
					viewMenu.hover = 2;
				}
				if(y>= 250 && y<300){
					viewMenu.hover = 3;
				}
				if(y>=300 && y<350){
					viewMenu.hover = 4;
				}
			}
		if(mainModel.getState() == State.MENU && viewMenu.section == 2){
			viewMenu.hover = 0;
		}
				
			
		}
		//while playing do this stuff for signs
		if(mainModel.getState() == State.PLAYING){
			//gets the block your mouse is in
			int mouseBlock = ((((y/20) * 40) ) + (x/20));
			//sets section to one less because section 4 is actually 3 for the array
			int s = mainModel.getLevel().getSecIn()-1;
			//city 1
			if(mainModel.getLevel().getCityIn() == 1){
				if(mainModel.getPlayer().outside){
					if(mainModel.getLevel().getSecIn() == 1){
						//sign positions
						if(mainModel.getLevel().city1[s][mouseBlock].isSign()){
							//purple house
							if(mouseBlock == 495){
								mainModel.getPlayer().HUD.centerMessage("Purple House", 0, x+10, y);
							}
							//orange house
							if(mouseBlock == 505){
								mainModel.getPlayer().HUD.centerMessage("Orange House", 0, x+10, y);
							}
							//red house
							if(mouseBlock == 895){
								mainModel.getPlayer().HUD.centerMessage("Red House", 0, x+10, y);
							}
							//blue house
							if(mouseBlock == 905){
								mainModel.getPlayer().HUD.centerMessage("Blue House", 0, x+10, y);
							}
						}else{
							//get rid of sign
							mainModel.getPlayer().HUD.dispSignText = false;
						}
					}
					if(mainModel.getLevel().getSecIn() == 2){
						if(mainModel.getLevel().city1[s][mouseBlock].isSign()){
							//bank
							if(mouseBlock == 496){
								mainModel.getPlayer().HUD.centerMessage("Bank", 0, x+10, y);
							}
							//police station
							if(mouseBlock == 768){
								mainModel.getPlayer().HUD.centerMessage("Police Station", 0, x+10, y);
							}
							//house 0
							if(mouseBlock == 909){
								mainModel.getPlayer().HUD.centerMessage(mainModel.getLevel().ownerHouse[0] + "'s House", 0, x+10, y);
							}
							//house 1
							if(mouseBlock == 912){
								mainModel.getPlayer().HUD.centerMessage(mainModel.getLevel().ownerHouse[1] + "'s House", 0, x+10, y);
							}
							//house 2
							if(mouseBlock == 1109){
								mainModel.getPlayer().HUD.centerMessage(mainModel.getLevel().ownerHouse[2] + "'s House", 0, x+10, y);
							}
							//house 3
							if(mouseBlock == 1112){
								mainModel.getPlayer().HUD.centerMessage(mainModel.getLevel().ownerHouse[3] + "'s House", 0, x+10, y);
							}
						}else{
							//remove sign
							mainModel.getPlayer().HUD.dispSignText = false;
						}
					}
					if(mainModel.getLevel().getSecIn() == 3){
						if(mainModel.getLevel().city1[s][mouseBlock].isSign()){
							//north school entrance
							if(mouseBlock == 488){
								mainModel.getPlayer().HUD.centerMessage("University of Zebule", 0, x+10, y);
							}
							//south school entrance
							if(mouseBlock == 1024){
								mainModel.getPlayer().HUD.centerMessage("University of Zebule Labratory", 0, x+10, y);
							}
						}else{
							//get rid of sign
							mainModel.getPlayer().HUD.dispSignText = false;
						}
					}
					if(mainModel.getLevel().getSecIn() == 4){
						if(mainModel.getLevel().city1[s][mouseBlock].isSign()){
							//bus station
							if(mouseBlock == 765){
								mainModel.getPlayer().HUD.centerMessage("Bus Station", 0, x+10, y);
							}
							//hotel
							if(mouseBlock == 817){
								mainModel.getPlayer().HUD.centerMessage("Hotel", 0, x+10, y);
							}
							//house 4
							if(mouseBlock == 204){
								mainModel.getPlayer().HUD.centerMessage(mainModel.getLevel().ownerHouse[4] + "'s House", 0, x+10, y);
							}
							//house 5
							if(mouseBlock == 212){
								mainModel.getPlayer().HUD.centerMessage(mainModel.getLevel().ownerHouse[5] + "'s House", 0, x+10, y);
							}
							//house 6
							if(mouseBlock == 225){
								mainModel.getPlayer().HUD.centerMessage(mainModel.getLevel().ownerHouse[6] + "'s House", 0, x+10, y);
							}
							//house 7
							if(mouseBlock == 234){
								mainModel.getPlayer().HUD.centerMessage(mainModel.getLevel().ownerHouse[7] + "'s House", 0, x+10, y);
							}
							//house 8
							if(mouseBlock == 345){
								mainModel.getPlayer().HUD.centerMessage(mainModel.getLevel().ownerHouse[8] + "'s House", 0, x+10, y);
							}
							//house 9
							if(mouseBlock == 354){
								mainModel.getPlayer().HUD.centerMessage(mainModel.getLevel().ownerHouse[9] + "'s House", 0, x+10, y);
							}
						}else{
							//get rid of sign
							mainModel.getPlayer().HUD.dispSignText = false;
						}
					}
					if(mainModel.getLevel().getSecIn() == 5){
						if(mainModel.getLevel().city1[s][mouseBlock].isSign()){
							//store
							if(mouseBlock == 377){
								mainModel.getPlayer().HUD.centerMessage("Bargain Palace", 0, x+10, y);
							}
							//club
							if(mouseBlock == 383){
								mainModel.getPlayer().HUD.centerMessage("Club Sizzle", 0, x+10, y);
							}
							//town hall
							if(mouseBlock == 776){
								mainModel.getPlayer().HUD.centerMessage("Town Hall", 0, x+10, y);
							}
						}else{
							//get rid of sign
							mainModel.getPlayer().HUD.dispSignText = false;
						}
					}
					if(mainModel.getLevel().getSecIn() == 6){
						if(mainModel.getLevel().city1[s][mouseBlock].isSign()){
							//monastery south entrance
							if(mouseBlock == 808){
								mainModel.getPlayer().HUD.centerMessage("Monastery South Wing", 0, x+10, y);
							}
							//monastery north entrance
							if(mouseBlock == 464){
								mainModel.getPlayer().HUD.centerMessage("Monastery North Wing", 0, x+10, y);
							}
						}else{
							//get rid of sign
							mainModel.getPlayer().HUD.dispSignText = false;
						}
					}
					if(mainModel.getLevel().getSecIn() == 7){
						//change this if you have any more signs in this square
						//black smith hut
						if(mainModel.getLevel().city1[s][mouseBlock].isSign()){
							mainModel.getPlayer().HUD.centerMessage("Black Smith's Hut", 0, x, y-50);
						}else{
							mainModel.getPlayer().HUD.dispSignText = false;
						}
					}
					if(mainModel.getLevel().getSecIn() == 8){
						//change this if you have any more signs in this square
						//lake sign
						if(mainModel.getLevel().city1[s][mouseBlock].isSign()){
							mainModel.getPlayer().HUD.centerMessage("Lake Fallacy Home Of Nessy", 0, x+10, y);
						}else{
							mainModel.getPlayer().HUD.dispSignText = false;
						}
					}
					if(mainModel.getLevel().getSecIn() == 9){
						//change this if you have any more signs in this square
						//Mine
						if(mainModel.getLevel().city1[s][mouseBlock].isSign()){
							mainModel.getPlayer().HUD.centerMessage("Mine", 0, x-100, y-50);
						}else{
							mainModel.getPlayer().HUD.dispSignText = false;
						}
					}
				}
			}
		}
		//if mouse moves while in the inventory set position so you can draw item
		if(mainModel.getState() == State.INVENTORY){
			mainModel.getPlayer().HUD.dragLoc(x, y);
		}
	}
	public void mouseClicked(MouseEvent me) {
		int x = me.getX();
		int y = me.getY();
		if(mainModel.getState() == State.MENU && viewMenu.section == 1){
			if(x>=500){
				if(y >= 150 && y<200){
					mainModel.setState(State.LOAD);
				}
				if(y >= 200 && y < 250){
					viewMenu.section = 2;
				}
				if(y >= 250 && y < 300){
					
				}
				if(y >= 300 && y < 350){
					mainModel.setState(State.DESTROYED);
					gameShutdown();
				}
			}
		}
		if(mainModel.getState() == State.MENU && viewMenu.section == 2){
			
		}
		if(mainModel.getState() == State.PLAYING){
			//TODO shoot features
			if(x==y){
				//got rid of warnings. delete on code time.
			}
			if(me.getButton() == 1){
				mainModel.getPlayer().HUD.cash += 100;
			}
			if(me.getButton() == 3){
				mainModel.getPlayer().HUD.cash -= 100;
			}
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		int x = me.getX();
		int y = me.getY();
		if(mainModel.getState() == State.INVENTORY){
			//if you click off it closes inventory and deletes any item in hand
			if(x > 700 || x < 100 || y > 500 || y < 100){
				if(mainModel.getPlayer().HUD.inMouse != mainModel.getPlayer().HUD.empty){
					mainModel.getPlayer().HUD.inMouse = mainModel.getPlayer().HUD.empty;
				}
				mainModel.getPlayer().HUD.dispInv = false;
				mainModel.setState(State.PLAYING);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		int x = me.getX();
		int y = me.getY();
		if(mainModel.getState() == State.INVENTORY){
			//checks all spaces for swaps
			int ax=150,by=125;
			for(int i = 1; i < mainModel.getPlayer().HUD.inventory.length; i++){
				if(ax > 600){
					by += 75;
					ax = 150;
				}
				if(x > ax && x < ax+50 && y > by && y < by+50){
					swapItemSwap(i);
				}
				
				ax+=75;
			}
			//checks in hand
			if(x > 565 && x < 615 && y > 425 && y < 475){
				swapItemSwap(0);
			}
		}
	}
	//used for swapping items around in the inventory
	private void swapItemSwap(int loc){
		mainModel.getPlayer().HUD.TEMP_HOLD = mainModel.getPlayer().HUD.inventory[loc];
		mainModel.getPlayer().HUD.inventory[loc] = mainModel.getPlayer().HUD.inMouse;
		mainModel.getPlayer().HUD.inMouse = mainModel.getPlayer().HUD.TEMP_HOLD;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

}
