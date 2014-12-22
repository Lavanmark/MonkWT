package monkWT.controller;

import monkWT.model.Player;
import monkWT.model.levels.Levels;

public class RandomMaleAI extends Player{

	int goTime = 0;
	boolean colOverride = false;
	
	public RandomMaleAI(Levels lvl) {
		super(lvl);
	}
	
	private static final int LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4;
	public void action(){
		int act = 0;
		if(goTime < 1 || colOverride){
			act = (int) ((Math.random()*10)%6);
			goTime = (int) ((Math.random()*1000)%100);
			colOverride = false;
		}
		if(this.sitting){
			   switch (act) {
				   case LEFT:
					   this.checkSitting();
					   break;
				   case RIGHT:
					   this.checkSitting();
					   break;
				   case UP:
					   this.checkSitting();
					   break;
				   case DOWN:
					   this.checkSitting();
					   break;
			   }
		   }else{
			switch(act){
			   case LEFT:
		            // left
		      		 this.pDir = 2;
		      		 this.moveCharCase(2);
		        	 break;
		         case RIGHT:
		            // right
		        	 this.pDir = 3;
		        	 this.moveCharCase(3);
		        	 break; 
		         case UP:
		        	 // up
		        	 this.pDir = 1;
		        	 this.moveCharCase(0);
		        	 break;
		         case DOWN:
		        	 // down
		        	 this.pDir = 0;
		        	 this.moveCharCase(1);
		        	 break;
		         case 5:
		        	 this.checkChair();
		        	 break;
			}
		   }
		this.update();
		
		goTime--;
	}
	@Override
	protected void colLeftRight(int dir){
		playerRect.x += dir;
		playerLeft.x += dir;
		playerRight.x += dir;
		playerTop.x += dir;
		playerFeet.x += dir;
		colOverride = true;
	}
	@Override
	protected void colUpDown(int dir){
		playerRect.y += dir;
		playerLeft.y += dir;
		playerRight.y += dir;
		playerTop.y += dir;
		playerFeet.y += dir;
		colOverride = true;
	}
	
}
