package monkWT.model;

import java.io.IOException;

import resources.ResourceLoader;

import monkWT.controller.SaveLoad;







public class Model {

	private Levels lvl;
	private Player player;
	private ResourceLoader rl = new ResourceLoader();
	
	public enum State {
		INITIALIZED, MENU, LOAD, STARTNEW, PLAYING, INVENTORY, PAUSED, GAMEOVER, DESTROYED
	}
	private State state;
	
	 private int currentLvl = 1;
	 private double bankCash = 0;
	 private double handCash = 0;
	 private int currentCity = 1;
	 private int currentQuest = 0;
	
	
	
	public Model(){
		lvl = new Levels();
		player = new Player();
	}
	
	
	public void update(){
		switch(getState()){
		case INITIALIZED:
			loadGame();
		  	setState(State.PLAYING);
		  	break;
		default:
			break;
		}
	}
	
	 public void gameUpdate() { 
		 //SPRINT
		 sprintChecks();
		 
		 //Stealth updating
		 stealthChecks();
		 //Check if hitting a door
		 checkDoor();
		 //Update the player
		 player.update();
		 //Double check that all player section is the same across the system
		 if(player.currentSec != lvl.playerInSec){
			 lvl.playerInSec = player.currentSec;
			 //make the sign disappear if the section changed
			 player.HUD.dispSignText = false;
		 }
		 //Double check that the city your in is the same as it says across the system
		 if(player.currentCity != lvl.playerInCity){
			 lvl.playerInCity = player.currentCity;
			 player.HUD.setCityName(player.currentCity);
		 }
		 //sync the HUD cash and hand cash
		 handCash = player.HUD.cash;
		 //check for lives
		 if(player.lives < 0){
			 setState(State.GAMEOVER);
		 }
	 }
	 
	 
	 
	 /*
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  */
	   public void checkDoor(){
		   //math for each corners square locations
		   int playerLocalLeftTop = (((((player.playerTop.y + player.yDirection)/20) * 40) ) + ((player.playerTop.x + player.xDirection)/20) ); 
		   int playerLocalRightTop = (((((player.playerTop.y + player.yDirection)/20) * 40) ) + ((player.playerTop.x + 14 + player.xDirection)/20) );
		   int playerLocalLeftBtm = (((((player.playerFeet.y + player.yDirection)/20) * 40) ) + ((player.playerFeet.x + player.xDirection)/20) ); 
		   int playerLocalRightBtm = (((((player.playerFeet.y + player.yDirection)/20) * 40) ) + ((player.playerFeet.x + 14 + player.xDirection)/20) );
		   
		   if(playerLocalLeftBtm > 1200){playerLocalLeftBtm -= 40;}
			if(playerLocalRightBtm > 1200){playerLocalRightBtm -= 40;}
			if(playerLocalLeftTop < 0){playerLocalLeftTop += 40;}
			if(playerLocalRightTop < 0){playerLocalRightTop += 40;}
		   
		   //TODO adding doors sucks. make it better
		   if(lvl.playerInCity == 1){
			   if(lvl.playerInside){
				   //Building 12 : Large Orange House Left Side
				   if(player.buildingIn == 12){
					   if(playerLocalLeftBtm == 579 || playerLocalRightBtm == 579
							   || playerLocalLeftBtm == 580 || playerLocalRightBtm == 580){
						   int xDist = -280;
						   int yDist = -160;
						   lvl.setBuildingEnt(0);
						   player.entBuilding(0, xDist, yDist);
					   }
				   }
				   //Building 13 : Large Orange House Right side
				   if(player.buildingIn == 13){
					   if(playerLocalLeftBtm == 579 || playerLocalRightBtm == 579
							   || playerLocalLeftBtm == 580 || playerLocalRightBtm == 580){
						   int xDist = -120;
						   int yDist = -160;
						   lvl.setBuildingEnt(0);
						   player.entBuilding(0, xDist, yDist);
					   }
				   }
				   //Building 14 : Small Orange House Top Left
				   if(player.buildingIn == 14){
					   if(playerLocalLeftBtm == 580 || playerLocalRightBtm == 580){
						   int xDist = 120;
						   int yDist = -160;
						   lvl.setBuildingEnt(0);
						   player.entBuilding(0, xDist, yDist);
					   }
				   }
				   //Building 15 : Small Orange House Top Right
				   if(player.buildingIn == 15){
					   if(playerLocalLeftBtm == 580 || playerLocalRightBtm == 580){
						   int xDist = 300;
						   int yDist = -160;
						   lvl.setBuildingEnt(0);
						   player.entBuilding(0, xDist, yDist);
					   }
				   }
				   //Building 16 : Small Orange House Bottom Left
				   if(player.buildingIn == 16){
					   if(playerLocalLeftTop == 380 || playerLocalRightTop == 380){
						   int xDist = 120;
						   int yDist = -40;
						   lvl.setBuildingEnt(0);
						   player.entBuilding(0, xDist, yDist);
					   }
				   }
				   //Building 17 : Small Orange House Bottom Right
				   if(player.buildingIn == 17){
					   if(playerLocalLeftTop == 380 || playerLocalRightTop == 380){
						   int xDist = 300;
						   int yDist = -40;
						   lvl.setBuildingEnt(0);
						   player.entBuilding(0, xDist, yDist);
					   }
				   }
				   //Building 18 : Bus Station
				   if(player.buildingIn == 18){
					   if(playerLocalRightTop == 627 || playerLocalRightBtm == 627
							   || playerLocalRightTop == 667 || playerLocalRightBtm == 667
							   || playerLocalRightTop == 707 || playerLocalRightBtm == 707 ){
						   int xDist = -400;
						   int yDist = 0;
						   lvl.setBuildingEnt(0);
						   player.entBuilding(0, xDist, yDist);
					   }
				   }
				 //Building 19 : Hotel
				   if(player.buildingIn == 19){
					   //entrance
					   if(playerLocalLeftTop > 577 && playerLocalLeftTop < 583 
							   || playerLocalRightTop > 577 && playerLocalRightTop < 583){
						   int xDist = 0;
						   int yDist = 100;
						   lvl.setBuildingEnt(0);
						   player.entBuilding(0, xDist, yDist);
					   }
					   //stairs
					   if(lvl.c1i.buildingSubSec == 1 && !player.justChanged){
						   if(playerLocalLeftBtm == 1197 || playerLocalRightBtm == 1197 && playerLocalLeftBtm !=1196
								   || playerLocalLeftBtm == 1198 || playerLocalRightBtm == 1198 ){
							   int xDist = 0;
							   int yDist = -20;
							   lvl.c1i.buildingSubSec = 2;
							   player.pDir = 1;
							   player.changeFloor(2);
							   player.entMove(xDist, yDist);
						   }
					   }
					   if(lvl.c1i.buildingSubSec == 2 && !player.justChanged){
						   if(playerLocalLeftBtm == 1197 || playerLocalRightBtm == 1197 && playerLocalLeftBtm !=1196
								   || playerLocalLeftBtm == 1198 || playerLocalRightBtm == 1198 ){
							   int xDist = 0;
							   int yDist = -20;
							   lvl.c1i.buildingSubSec = 1;
							   player.pDir = 1;
							   player.changeFloor(1);
							   player.entMove(xDist, yDist);
						   }
					   }					   
				   }
				 //Building 21 : Club
				   if(player.buildingIn == 21){
					   if(playerLocalLeftTop == 525 || playerLocalLeftBtm == 525
							   || playerLocalLeftTop == 565 || playerLocalLeftBtm == 565
							   || playerLocalLeftTop == 605 || playerLocalLeftBtm == 605 ){
						   int xDist = 340;
						   int yDist = -140;
						   lvl.setBuildingEnt(0);
						   player.entBuilding(0, xDist, yDist);
					   }
				   }
			   }else{
				   if(lvl.playerInSec == 1){
					   
				   }
				   if(lvl.playerInSec == 2){
					   
				   }
				   if(lvl.playerInSec == 3){
					   
				   }
				   if(lvl.playerInSec == 4){
					   //Building 12 : Large Orange House Left Side
					   if(playerLocalLeftTop == 165 || playerLocalRightTop == 165
							   || playerLocalLeftTop == 166 || playerLocalRightTop == 166){
						   int xDist = 280;
						   int yDist = 160;
						   lvl.setBuildingEnt(12);
						   player.entBuilding(12, xDist, yDist);
						   stopSprint();
					   }
					   //Building 13 : Large Orange House Right side
					   if(playerLocalLeftTop == 173 || playerLocalRightTop == 173
							   || playerLocalLeftTop == 174 || playerLocalRightTop == 174){
						   int xDist = 120;
						   int yDist = 160;
						   lvl.setBuildingEnt(13);
						   player.entBuilding(13, xDist, yDist);
						   stopSprint();
					   }
					   //Building 14 : Small Orange House Top Left Side
					   if(playerLocalLeftTop == 186 || playerLocalRightTop == 186){
						   int xDist = -120;
						   int yDist = 160;
						   lvl.setBuildingEnt(14);
						   player.entBuilding(14, xDist, yDist);
						   stopSprint();
					   }
					   //Building 15 : Small Orange House Top Right Side
					   if(playerLocalLeftTop == 195 || playerLocalRightTop == 195){
						   int xDist = -300;
						   int yDist = 160;
						   lvl.setBuildingEnt(15);
						   player.entBuilding(15, xDist, yDist);
						   stopSprint();
					   }
					   //Building 16 : Small Orange House Bottom Left Side
					   if(playerLocalLeftBtm == 386 || playerLocalRightBtm == 386){
						   int xDist = -120;
						   int yDist = 40;
						   lvl.setBuildingEnt(16);
						   player.entBuilding(16, xDist, yDist);
						   stopSprint();
					   }
					   //Building 17 : Small Orange House Bottom Right Side
					   if(playerLocalLeftBtm == 395 || playerLocalRightBtm == 395){
						   int xDist = -300;
						   int yDist = 40;
						   lvl.setBuildingEnt(17);
						   player.entBuilding(17, xDist, yDist);
						   stopSprint();
					   }
					   //Building 18 : Bus Station
					   if(playerLocalLeftTop == 605 || playerLocalLeftBtm == 605
							   || playerLocalLeftTop == 645 || playerLocalLeftBtm == 645
							   || playerLocalLeftTop == 685 || playerLocalLeftBtm == 685 ){
						   int xDist = 400;
						   int yDist = 0;
						   lvl.setBuildingEnt(18);
						   player.entBuilding(18, xDist, yDist);
						   stopSprint();
					   }
					   //Building 19 : Hotel
					   if(playerLocalLeftBtm > 857 && playerLocalLeftBtm < 863 
							   || playerLocalRightBtm > 857 && playerLocalRightBtm < 863){
						   int xDist = 0;
						   int yDist = -100;
						   lvl.setBuildingEnt(19);
						   player.entBuilding(19, xDist, yDist);
						   stopSprint();
					   }
					   
				   }
				   if(lvl.playerInSec == 5){
					   //Building 21 : Club
					   if(playerLocalRightTop == 264 || playerLocalRightBtm == 264 
							   || playerLocalRightTop == 304 || playerLocalRightBtm == 304
							   || playerLocalRightTop == 344 || playerLocalRightBtm == 344){
						   int xDist = -340;
						   int yDist = 140;
						   lvl.setBuildingEnt(21);
						   player.entBuilding(21, xDist, yDist);
						   stopSprint();
					   }
				   }
				   if(lvl.playerInSec == 6){
					   if((playerLocalRightBtm > 848 && playerLocalRightBtm < 855 ) || (playerLocalLeftBtm > 848 && playerLocalLeftBtm < 855)){
						   int xDist = -80;
						   int yDist = -40;
						   lvl.setBuildingEnt(24);
						   player.entBuilding(24, xDist, yDist);
						   stopSprint();
					   }
				   }
				   if(lvl.playerInSec == 7){
					   
				   }
				   if(lvl.playerInSec == 8){
					   
				   }
				   if(lvl.playerInSec == 9){
					   
				   }	   
			   }
		   }
	   }
	   
	   //stops the player from sprinting
	   private void stopSprint(){
		   player.HUD.sprint = 1;
		   player.HUD.dispSignText = false;
		   if(player.xDirection == 2){player.xDirection = 1;}
		   if(player.xDirection == -2){player.xDirection = -1;}
		   if(player.yDirection == 2){player.yDirection = 1;}
		   if(player.yDirection == -2){player.yDirection = -1;}
	   }
	   
	   private void sprintChecks(){
		   if(player.HUD.sprint == 2){
			   //if not moving but pressing sprint refill stamina
			   if(player.xDirection == 0 && player.yDirection == 0){   
				   if(player.HUD.sprintTime < 500){
					   //while the sprint time is less than 500 add one
					   player.HUD.sprintTime += 1;
				   }else if(player.HUD.sprintTime > 500){
					   //if the sprint time is over 500 stop it at 500
					   player.HUD.sprintTime = 500;
				   }
			   }else{
				   //if moving remove one from sprint time
				   player.HUD.sprintTime -= 1;
			   }
			   //if sprint time runs out then stop sprint and reset cool down time
			   if(player.HUD.sprintTime <= 0){
				   player.HUD.sprint = 1;
				   player.HUD.sprintCoolDown = 250;
			   }
		   }else{
			   //if you aren't sprinting and there is more cool down time then add some sprint time and take away cool down time
			   if(player.HUD.sprintCoolDown > 0){
				   player.HUD.sprintCoolDown -= 1;
				   player.HUD.sprintTime = -2*player.HUD.sprintCoolDown+500;
			   }else{
				   //add some if you aren't sprinting and your sprint meter isn't full
				   if(player.HUD.sprintTime < 500){
					   player.HUD.sprintTime += 1;
				   }else if(player.HUD.sprintTime > 500){
					   //if its more than 500 then set it to 500
					   player.HUD.sprintTime = 500;
				   }
			   }
		   }
	   }
	   
	   //checks different aspects to adjust the level of stealth
	   @SuppressWarnings("unused")
	   private void stealthChecks(){
		   //You loose stealth when you sprint, walk near people, pull out a weapon, talk to people.
		   //add stealth when far away from people, standing still, power ups?
		   int sprintPenalty = 2;
		   int walkingPenalty = 1;
		   int weaponPenalty = 4;
		   int locationPenalty = 1;
		   int talkPenalty = 3;
		   //sprint takes away all stealth
		   if(player.HUD.sprint == 2 && player.moving){
			   player.HUD.stealthAmt = 0;
		   }
		   //use later to measure distance from people. Use with nearPersonStealthCheck();
		   int playerLocalLeftTop = (((((player.playerTop.y + player.yDirection)/20) * 40) ) + ((player.playerTop.x + player.xDirection)/20) ); 
		   int playerLocalRightTop = (((((player.playerTop.y + player.yDirection)/20) * 40) ) + ((player.playerTop.x + 14 + player.xDirection)/20) );
		   int playerLocalLeftBtm = (((((player.playerFeet.y + player.yDirection)/20) * 40) ) + ((player.playerFeet.x + player.xDirection)/20) ); 
		   int playerLocalRightBtm = (((((player.playerFeet.y + player.yDirection)/20) * 40) ) + ((player.playerFeet.x + 14 + player.xDirection)/20) );
		   
		   if(playerLocalLeftBtm > 1200){playerLocalLeftBtm -= 40;}
		   if(playerLocalRightBtm > 1200){playerLocalRightBtm -= 40;}
		   if(playerLocalLeftTop < 0){playerLocalLeftTop += 40;}
		   if(playerLocalRightTop < 0){playerLocalRightTop += 40;}
		   //walking takes away 1
		   if(player.moving){
			   if(player.HUD.stealthAmt > 0){
				   player.HUD.stealthAmt -= walkingPenalty;
			   }
		   }else{
			   //standing still adds 2
			   player.HUD.stealthAmt += sprintPenalty;
		   }
		   //sitting regenerates everything
		   if(player.sitting){
			   player.HUD.stealthAmt = 500;
		   }
		   //if stealth goes over or under then reset it
		   if(player.HUD.stealthAmt > 500){
			   player.HUD.stealthAmt = 500;
		   }else if(player.HUD.stealthAmt < 0){
			   player.HUD.stealthAmt = 0;
		   }
	   }
	   
	   @SuppressWarnings("unused")
	   private boolean nearPersonStealthCheck(int PLLT, int PLLB, int PLRT, int PLRB){
		   boolean near = false;
		   
		   
		   
		   return near;
	   }
	 
	 
	 
	 /*
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  */
	 
	
	
	private SaveLoad contrlSaveLoad;
	public void prepareLoad(SaveLoad sl){
		contrlSaveLoad = sl;
	}
	private void loadGame(){
		try{
			contrlSaveLoad.load();
	  	}catch(Exception e){
	  		e.printStackTrace();
	  	}
	  
	  	  //load main
		currentCity = contrlSaveLoad.getCity();
		currentLvl = contrlSaveLoad.getSec();
		handCash = contrlSaveLoad.getCash();
		bankCash = contrlSaveLoad.getBankCash();
		currentQuest = contrlSaveLoad.getQuest();
		//load levels
		lvl.cityDeaths = contrlSaveLoad.getDeaths();
		lvl.loadCity(currentCity);
		lvl.playerInSec = currentLvl;
		lvl.setBuildingEnt(contrlSaveLoad.getBuilding());
		lvl.ownerHouse = contrlSaveLoad.getOwners();
		//load player
		player.playerInitialize(currentCity);
		player.currentCity = currentCity;
		player.currentSec = currentLvl;
		player.buildingIn = contrlSaveLoad.getBuilding();
		player.outside = contrlSaveLoad.getOutside();
		player.entBuilding(contrlSaveLoad.getBuilding(),0,0);
		player.loadPosition(contrlSaveLoad.getXLoc(), contrlSaveLoad.getYLoc());
		player.setDeathsForColl(contrlSaveLoad.getDeaths());
		//load HUD
		player.HUD.HUDInitialize();
		player.HUD.setCityName(lvl.playerInCity);
		player.HUD.setQuest(currentQuest);
		player.HUD.cash = handCash;
		int[] tempSlist = contrlSaveLoad.getInv();
		//Reset all items
		for(int i = 0; i < tempSlist.length; i++){
			//TODO finish adding all items. How to implement damage and strength??
			if(tempSlist[i] == 000){
				try {
					player.HUD.inventory[i] = new Item(tempSlist[i],0,0,rl.getIcon("blankIcon.png"),"",0,0,0,0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(tempSlist[i] == 001){
				try {
					player.HUD.inventory[i] = new Item(001,5,100,rl.getIcon("spoonIcon.png"),"spoon",0,0,10,4);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(tempSlist[i] == 020){
				try{
					player.HUD.inventory[i] = new Item(020,25,100,rl.getIcon("daggerIcon.png"),"dagger",0,0,10,5);
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}

	
	

	public Player getPlayer(){
		return player;
	}
	public Levels getLevel(){
		return lvl;
	}
	
	
	public State getState() {
		return state;
	}
	public  void setState(State state) {
		this.state = state;
	}
	
	
}
