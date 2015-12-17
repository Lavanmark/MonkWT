package monkWT.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


import monkWT.model.Model;
import monkWT.model.Model.State;


public class MonkKeyListener implements KeyListener{

	private Model mainModel;
	private SaveLoad contrlSaveLoad;
	
	public MonkKeyListener(Model m){
		mainModel = m;
	}
	public void setSaveLoad(SaveLoad sl){
		contrlSaveLoad = sl;
	}
	
	private void inventoryKeyPressed(int key){
		switch(key){
		case KeyEvent.VK_B:
			mainModel.getPlayer().closeInventory();
			key = 0;
			mainModel.setState(State.PLAYING);
			break;
		}
	}
	
	private void playingKeyPressed(int key){
		if(mainModel.getPlayer().sitting){
			   switch (key) {
				   case KeyEvent.VK_A:
					   mainModel.getPlayer().checkSitting();
					   break;
				   case KeyEvent.VK_D:
					   mainModel.getPlayer().checkSitting();
					   break;
				   case KeyEvent.VK_W:
					   mainModel.getPlayer().checkSitting();
					   break;
				   case KeyEvent.VK_S:
					   mainModel.getPlayer().checkSitting();
					   break;
				   case KeyEvent.VK_B:
					   mainModel.getPlayer().openInventory();
					   key = 0;
					   mainModel.setState(State.INVENTORY);
					   break;
			   }
		   }else{
			   switch(key){
			   case KeyEvent.VK_A:
		            // left
		      		 mainModel.getPlayer().pDir = 2;
		      		 if(mainModel.getPlayer().HUD.sprint == 1){
		      			 mainModel.getPlayer().moveCharCase(2);
		      		 }else{
		      			 mainModel.getPlayer().moveCharCase(6);
		      		 }
		        	 break;
		         case KeyEvent.VK_D:
		            // right
		        	 mainModel.getPlayer().pDir = 3;
		        	 if(mainModel.getPlayer().HUD.sprint == 1){
		        		 mainModel.getPlayer().moveCharCase(3);
		        	 }else{
		        		 mainModel.getPlayer().moveCharCase(7);
		        	 }
		        	 break;
		         case KeyEvent.VK_W:
		        	 // up
		        	 mainModel.getPlayer().pDir = 1;
		        	 if(mainModel.getPlayer().HUD.sprint == 1){
		        		 mainModel.getPlayer().moveCharCase(0);
		        	 }else{
		        		 mainModel.getPlayer().moveCharCase(4);
		        	 }
		        	 break;
		         case KeyEvent.VK_S:
		        	 // down
		        	 mainModel.getPlayer().pDir = 0;
		        	 if(mainModel.getPlayer().HUD.sprint == 1){
		        		 mainModel.getPlayer().moveCharCase(1);
		        	 }else{
		        		 mainModel.getPlayer().moveCharCase(5);
		        	 }
		        	 break;
		         case KeyEvent.VK_SHIFT:
		        	 //sprint
		        	 if(!mainModel.getPlayer().getInside()){
			        	 if(mainModel.getPlayer().HUD.sprintCoolDown <= 0){
			        		 mainModel.getPlayer().HUD.sprint = 2;
				        	 if(mainModel.getPlayer().xDirection == 1){mainModel.getPlayer().xDirection = 2;}
				        	 if(mainModel.getPlayer().xDirection == -1){mainModel.getPlayer().xDirection = -2;}
				        	 if(mainModel.getPlayer().yDirection == 1){mainModel.getPlayer().yDirection = 2;}
				        	 if(mainModel.getPlayer().yDirection == -1){mainModel.getPlayer().yDirection = -2;}
			        	 }
		        	 }else{
		        		 mainModel.getPlayer().HUD.sprint = 1;
		        		 if(mainModel.getPlayer().xDirection == 2){mainModel.getPlayer().xDirection = 1;}
			        	 if(mainModel.getPlayer().xDirection == -2){mainModel.getPlayer().xDirection = -1;}
			        	 if(mainModel.getPlayer().yDirection == 2){mainModel.getPlayer().yDirection = 1;}
			        	 if(mainModel.getPlayer().yDirection == -2){mainModel.getPlayer().yDirection = -1;}
		        	 }
		        	 break;
		         case KeyEvent.VK_Q:
		        	 //action
		        	 mainModel.getPlayer().moveCharCase(8);
		        	 
		        	 break;
		         case KeyEvent.VK_B:
		        	 mainModel.getPlayer().openInventory();
		        	 mainModel.setState(State.INVENTORY);
		        	 key = 0;
		        	 break;
		         case KeyEvent.VK_Z:
		        	 //TODO add in different way of saving. 
		        	 try{
		        		 if(!mainModel.getPlayer().sitting){
		        			 contrlSaveLoad.save(mainModel.getPlayer().getCurrentCity(), mainModel.getPlayer().getCurrentSec(),
		        					 mainModel.getPlayer().getCurrentBuilding(), mainModel.getPlayer().getInside(), mainModel.getPlayer().playerRect.x,
		        					 mainModel.getPlayer().playerRect.y, mainModel.getHandCash(), mainModel.getBankCash(), mainModel.getCurrentQuest(), 
		        					 mainModel.getLevel().cityDeaths, mainModel.getLevel().ownerHouse, mainModel.getPlayer().HUD.inventory);
		        			 mainModel.getPlayer().HUD.goSave = true;
		        		 }
		        	 }catch(IOException e){
		        		 e.printStackTrace();
		        	 }
		        	 break;
		        case KeyEvent.VK_G:
		        	mainModel.getPlayer().HUD.inventory[4] = mainModel.getPlayer().HUD.spoon;
		        	break;
			   }
		   }
	}
	
	private void gameKeyReleased(int key){
		switch(key){
		case KeyEvent.VK_W:
		   mainModel.getPlayer().stopMoveCharY();
		   if(mainModel.getState() == State.PLAYING){
			   mainModel.getPlayer().checkImgDir();
		   }
		   break;
	   case KeyEvent.VK_S:
		   mainModel.getPlayer().stopMoveCharY();
		   if(mainModel.getState() == State.PLAYING){
			   mainModel.getPlayer().checkImgDir();
		   }
		   break;
	   case KeyEvent.VK_A:
		   mainModel.getPlayer().stopMoveCharX();
		   if(mainModel.getState() == State.PLAYING){
			   mainModel.getPlayer().checkImgDir();
		   }			   
		   break;
	   case KeyEvent.VK_D:
		   mainModel.getPlayer().stopMoveCharX();
		   if(mainModel.getState() == State.PLAYING){
			   mainModel.getPlayer().checkImgDir();
		   }
		   break;
	   case KeyEvent.VK_C:
			   mainModel.getPlayer().checkChair();
		   break;
	   case KeyEvent.VK_SHIFT:
		   
		   	 mainModel.getPlayer().HUD.sprint = 1;
		   	 if(mainModel.getState() == State.PLAYING){
			   	 if(mainModel.getPlayer().xDirection == 2){mainModel.getPlayer().xDirection = 1; mainModel.getPlayer().checkImgDir();}
	        	 if(mainModel.getPlayer().xDirection == -2){mainModel.getPlayer().xDirection = -1; mainModel.getPlayer().checkImgDir();}
	        	 if(mainModel.getPlayer().yDirection == 2){mainModel.getPlayer().yDirection = 1; mainModel.getPlayer().checkImgDir();}
	        	 if(mainModel.getPlayer().yDirection == -2){mainModel.getPlayer().yDirection = -1; mainModel.getPlayer().checkImgDir();}
		   	 }
		   break;
	   }  
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		switch(mainModel.getState()){
		case INVENTORY:
			inventoryKeyPressed(ke.getKeyCode());
			break;
		case PLAYING:
			playingKeyPressed(ke.getKeyCode());
			break;
		default:
			break;
		}
		//gameKeyPressed(ke.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		gameKeyReleased(ke.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		
	}

}
