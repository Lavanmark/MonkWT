package monkWT;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;





import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import monkWT.controller.SaveLoad;
import monkWT.model.Item;
import monkWT.model.Levels;
import monkWT.model.Player;
import monkWT.view.Menu;


import resources.ResourceLoader;

/**************************
 *                        *
 * @author Tyler Draughon *
 *                        *
 **************************/

public class Main extends JFrame {     
	
	private static final long serialVersionUID = 1L;
// main class for the game as a Swing application
	   // Define constants for the game
	   static final int CANVAS_WIDTH = 800;    // width and height of the game screen
	   static final int CANVAS_HEIGHT = 600;
	   static final int UPDATE_RATE = 50;    // number of game update per second
	   static final long UPDATE_PERIOD = 1000000000L / UPDATE_RATE;  // nanoseconds
	 
	   // Enumeration for the states of the game.
	   static enum State {
	      INITIALIZED, MENU, LOAD, STARTNEW, PLAYING, INVENTORY, PAUSED, GAMEOVER, DESTROYED
	   }
	   static State state;   // current state of the game
	   
	   // Define instance variables for the game objects
	   private int currentLvl = 1;
	   private double bankCash = 0;
	   private double handCash = 0;
	   private int currentCity = 1;
	   private int currentQuest = 0;
	   
	   //Character Variables
	   
	   
	   //Private class definitions
	   private static ResourceLoader rl = new ResourceLoader();
	   private Menu menu;
	   private Levels lvl;
	   private Player player;
	   private SaveLoad saveLoad;
	   
	   
	   //Images for system
	   public Image gameIcon = rl.getIcon("gameIcon.png");
	   
	   
	   
	   // Handle for the custom import monkWT.Main.State;
	   //drawing panel
	   private GameCanvas canvas;
	   
	   // Constructor to initialize the UI components and game objects
	   public Main() {
	      // Initialize the game objects
	      gameInit();
	   
	      // UI components
	      canvas = new GameCanvas();
	      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
	      this.setContentPane(canvas);
	      // Other UI components such as button, score board, if any.
	      // ......
	      this.setIconImage(gameIcon);
	      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	      this.pack();
	      //do most things after packing it
	      this.setLocationRelativeTo(null);
	      this.setTitle("Monk: Worldly Things");
	      this.setVisible(true);
	      
	      gameStart();
	   }
	   
	   // All the game related codes here
	   
	   // Initialize all the game objects, run only once in the constructor of the main class.
	   public void gameInit() {
		   
		   menu = new Menu();
		   lvl = new Levels();
		   player = new Player();
		   saveLoad = new SaveLoad();
		   
		   state = State.INITIALIZED;
	   }
	   
	   
	   
	   // Shutdown the game, clean up code that runs only once.
	   public void gameShutdown() {
		  if(state == State.DESTROYED){
			  //save game?
			  System.exit(0);
			  
		  }
	   }
	   
	   
	   // To start and re-start the game.
	   public void gameStart() { 
	      // Create a new thread
	      Thread gameThread =  new Thread() {
	         // Override run() to provide the running behavior of this thread.
	         @Override
	         public void run() {
	            gameLoop();
	         }
	      };
	      // Start the thread. start() calls run(), which in turn calls gameLoop().
	      gameThread.start();
	   }
	   //TODO add all doors
	   //checks if you hit a door
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
	   
	   // Run the game loop here.
	   private void gameLoop() {
	      // Regenerate the game objects for a new game
	      // ......
	      
	   
	      // Game loop
	      long beginTime, timeTaken, timeLeft;
	      
	      if(state == State.INITIALIZED){
	    	  state = State.MENU;
	    	  log("in game and menu state");
	    	  //if the game was initialized then draw the menu things
	    	  while(state == State.MENU){
	    		  
	    		  
	    		  repaint();
	    	  }
	      }
	      //TODO finish load
	      //load the game then start it
	      if(state == State.LOAD){
	    	  repaint();
	    	  try{
	    		  saveLoad.load();
	    	  }catch(Exception e){
	    		  e.printStackTrace();
	    	  }
	    	  
	    	  //load main
	    	  currentCity = saveLoad.getCity();
	    	  currentLvl = saveLoad.getSec();
	    	  handCash = saveLoad.getCash();
	    	  bankCash = saveLoad.getBankCash();
	    	  currentQuest = saveLoad.getQuest();
	    	  //load levels
	    	  lvl.cityDeaths = saveLoad.getDeaths();
	    	  lvl.loadCity(currentCity);
	    	  lvl.playerInSec = currentLvl;
	    	  lvl.setBuildingEnt(saveLoad.getBuilding());
	    	  lvl.ownerHouse = saveLoad.getOwners();
	    	  //load player
	    	  player.playerInitialize(currentCity);
	    	  player.currentCity = currentCity;
	    	  player.currentSec = currentLvl;
	    	  player.buildingIn = saveLoad.getBuilding();
	    	  player.outside = saveLoad.getOutside();
	    	  player.entBuilding(saveLoad.getBuilding(),0,0);
	    	  player.loadPosition(saveLoad.getXLoc(), saveLoad.getYLoc());
	    	  player.setDeathsForColl(saveLoad.getDeaths());
	    	  //load HUD
	    	  player.HUD.HUDInitialize();
	    	  player.HUD.setCityName(lvl.playerInCity);
	    	  player.HUD.setQuest(currentQuest);
	    	  player.HUD.cash = handCash;
	    	  int[] tempSlist = saveLoad.getInv();
	    	  //Reset all items
	    	  for(int i = 0; i < tempSlist.length; i++){
	    		  //TODO finish adding all items. How to implement damage and strength??
	    		  if(tempSlist[i] == 000){
	    			  try {
						player.HUD.inventory[i] = new Item(000,0,0,rl.getIcon("blankIcon.png"),"",0,0,0,0);
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
	    			  try {
						player.HUD.inventory[i] = new Item(020,25,100,rl.getIcon("daggerIcon.png"),"dagger",0,0,10,5);
					} catch (IOException e) {
						e.printStackTrace();
					}
	    		  }
	    	  }
	    	  state = State.PLAYING;
	      }
	      
	      
	      if(state == State.PLAYING){
	    	  
	    	  while (true) {
	    		 
		         beginTime = System.nanoTime();
		         if (state == State.GAMEOVER){
		        	 //menu.section = 3;
		        	 
		        	 // break the loop to finish the current play
		         }
		         if(state == State.PAUSED){
		        	 // while paused do this stuff
		        	 
		         }
		         
		         if(state == State.INVENTORY){
		        	// during the inventory stlong beginTime, timeTaken, timeLeft;ate do this stuff
		         }
		         
		         
		         if (state == State.PLAYING) {
		            // Update the state and position of all the game objects,
		            // detect collisions and provide responses.
		            gameUpdate();
		         }
		         // Refresh the display
		         repaint();
		         
		         // Delay timer to provide the necessary delay to meet the target rate
		         timeTaken = System.nanoTime() - beginTime;
		         timeLeft = (UPDATE_PERIOD - timeTaken) / 1000000L;  // in milliseconds
		         if (timeLeft < 10) timeLeft = 6;   // set a minimum
		         
		         try {
		            // Provides the necessary delay and also yields control so that other thread can do work.
		            Thread.sleep(timeLeft);
		            
		         } catch (InterruptedException ex) { }
		      }	
	      }
	   }
	   
	   // Update the state and position of all the game objects,
	   // detect collisions and provide responses.
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
			   state = State.GAMEOVER;
		   }
	   }
	   
	   // Refresh the display. Called back via repaint(), which invoke the paintComponent().
	   
	private void gameDraw(Graphics2D g2d) {
	      switch (state) {
	         case INITIALIZED:
	            // ......
	            break;
	         case MENU:
	        	 //draw the menu
	        	 menu.drawMain(g2d);
	        	break;
	         case LOAD:
	        	 //draw the load thing while its loading 
	        	 menu.drawLoad(g2d);
	        	 break;
	         case PLAYING:
	             //draw the main map
	        	 lvl.draw(g2d);
	        	 //draw the player over map
	        	 player.draw(g2d);
	        	 //draw trees over player and map
	        	 lvl.drawTrees(g2d);
	        	 //draw HUD over everything
	        	 player.HUD.draw(g2d);
	        	 
	            break;
	         case INVENTORY:
	        	 lvl.draw(g2d);
	        	 player.draw(g2d);
	        	 lvl.drawTrees(g2d);
	        	 player.HUD.draw(g2d);
	        	 break;
	         case PAUSED:
	        	 lvl.draw(g2d);
	        	 player.draw(g2d);
	        	 lvl.drawTrees(g2d);
	        	 player.HUD.draw(g2d);
	            break;
	         case GAMEOVER:
	            menu.drawMain(g2d);
	            break;
		default:
			break;
	         
	      } 
	   }
	   
	   // Process a key-pressed event. Update the current state.
	   public void gameKeyPressed(int keyCode){
		   if(state == State.INVENTORY){
				switch(keyCode){
					case KeyEvent.VK_B:
						player.HUD.dispInv = false;
						if(player.HUD.inMouse != player.HUD.empty){
							//if bag is closed with object in hand then put it in first open one
							for(int i = 1; i < player.HUD.inventory.length; i++){
								if(player.HUD.inventory[i].getItemNum() == player.HUD.empty.getItemNum()){
									player.HUD.inventory[i] = player.HUD.inMouse;
									player.HUD.inMouse = player.HUD.empty;
								}
							}
							if(player.HUD.inMouse != player.HUD.empty && player.HUD.inventory[0] == player.HUD.empty){
								player.HUD.inventory[0] = player.HUD.inMouse;
							}
						}
						keyCode = 0;
						state = State.PLAYING;
						break;
				}
			}
		   if(state == State.PLAYING){
			   if(player.sitting){
				   switch (keyCode) {
					   case KeyEvent.VK_A:
						   player.checkSitting();
						   break;
					   case KeyEvent.VK_D:
						   player.checkSitting();
						   break;
					   case KeyEvent.VK_W:
						   player.checkSitting();
						   break;
					   case KeyEvent.VK_S:
						   player.checkSitting();
						   break;
					   case KeyEvent.VK_B:
						   	 player.HUD.dispInv = true;
						   	 player.HUD.dispSignText = false;
						   	 if(player.HUD.inMouse != player.HUD.empty){
								 //if bag is closed with object in hand then put it in first open one
								 for(int i = 1; i < player.HUD.inventory.length; i++){
									 if(player.HUD.inventory[i].getItemNum() == player.HUD.empty.getItemNum()){
									 	 player.HUD.inventory[i] = player.HUD.inMouse;
									 	 player.HUD.inMouse = player.HUD.empty;
									 }
								 }
								 if(player.HUD.inMouse != player.HUD.empty && player.HUD.inventory[0] == player.HUD.empty){
								 	 player.HUD.inventory[0] = player.HUD.inMouse;
								 }
							 }
						   	 keyCode = 0;
				        	 state = State.INVENTORY;
				        	 break;
				   }
			   }
			   switch (keyCode) {
			   		case KeyEvent.VK_A:
			            // left
			      		 player.pDir = 2;
			      		 if(player.HUD.sprint == 1){
			      			 player.moveCharCase(2);
			      		 }else{
			      			 player.moveCharCase(6);
			      		 }
			        	 break;
			         case KeyEvent.VK_D:
			            // right
			        	 player.pDir = 3;
			        	 if(player.HUD.sprint == 1){
			        		 player.moveCharCase(3);
			        	 }else{
			        		 player.moveCharCase(7);
			        	 }
			        	 break;
			         case KeyEvent.VK_W:
			        	 // up
			        	 player.pDir = 1;
			        	 if(player.HUD.sprint == 1){
			        		 player.moveCharCase(0);
			        	 }else{
			        		 player.moveCharCase(4);
			        	 }
			        	 break;
			         case KeyEvent.VK_S:
			        	 // down
			        	 player.pDir = 0;
			        	 if(player.HUD.sprint == 1){
			        		 player.moveCharCase(1);
			        	 }else{
			        		 player.moveCharCase(5);
			        	 }
			        	 break;
			         case KeyEvent.VK_SHIFT:
			        	 //sprint
			        	 if(player.outside){
				        	 if(player.HUD.sprintCoolDown <= 0){
				        		 player.HUD.sprint = 2;
					        	 if(player.xDirection == 1){player.xDirection = 2;}
					        	 if(player.xDirection == -1){player.xDirection = -2;}
					        	 if(player.yDirection == 1){player.yDirection = 2;}
					        	 if(player.yDirection == -1){player.yDirection = -2;}
				        	 }
			        	 }else{
			        		 player.HUD.sprint = 1;
			        		 if(player.xDirection == 2){player.xDirection = 1;}
				        	 if(player.xDirection == -2){player.xDirection = -1;}
				        	 if(player.yDirection == 2){player.yDirection = 1;}
				        	 if(player.yDirection == -2){player.yDirection = -1;}
			        	 }
			        	 break;
			         case KeyEvent.VK_Q:
			        	 //action
			        	 player.moveCharCase(8);
			        	 
			        	 break;
			         case KeyEvent.VK_B:
			        	 player.HUD.dispInv = true;
			        	 player.HUD.dispSignText = false;
			        	 if(player.HUD.inMouse != player.HUD.empty){
							 for(int i = 1; i < player.HUD.inventory.length; i++){
								 //if bag is closed with object in hand then put it in first open one
								 if(player.HUD.inventory[i].getItemNum() == player.HUD.empty.getItemNum()){
									 player.HUD.inventory[i] = player.HUD.inMouse;
									 player.HUD.inMouse = player.HUD.empty;
								 }
							 }
							 if(player.HUD.inMouse != player.HUD.empty && player.HUD.inventory[0] == player.HUD.empty){
								 player.HUD.inventory[0] = player.HUD.inMouse;
							 }
						 }
			        	 keyCode = 0;
			        	 state = State.INVENTORY;
			        	 break;
			         case KeyEvent.VK_Z:
			        	 //TODO add in different way of saving. 
			        	 try{
			        		 if(!player.sitting){
			        			 saveLoad.save(player.currentCity, player.currentSec, player.buildingIn, player.outside, player.playerRect.x,
			        					 player.playerRect.y, handCash, bankCash, currentQuest, lvl.cityDeaths, lvl.ownerHouse, player.HUD.inventory);
			        			 player.HUD.goSave = true;
			        		 }
			        	 }catch(IOException e){
			        		 e.printStackTrace();
			        	 }
			        	 break;
			        case KeyEvent.VK_G:
			        	player.HUD.inventory[4] = player.HUD.spoon;
			        	break;
			 }
		}

		}
	        
	      
	   
	   
	   // Process a key-released event.
	   public void gameKeyReleased(int keyCode) {  
		   
		   switch (keyCode) {
		   case KeyEvent.VK_W:
			   player.stopMoveCharY();
			   if(state == State.PLAYING){
				   player.checkImgDir();
			   }
			   break;
		   case KeyEvent.VK_S:
			   player.stopMoveCharY();
			   if(state == State.PLAYING){
				   player.checkImgDir();
			   }
			   break;
		   case KeyEvent.VK_A:
			   player.stopMoveCharX();
			   if(state == State.PLAYING){
				   player.checkImgDir();
			   }			   
			   break;
		   case KeyEvent.VK_D:
			   player.stopMoveCharX();
			   if(state == State.PLAYING){
				   player.checkImgDir();
			   }
			   break;
		   case KeyEvent.VK_C:
				   player.checkChair();
			   break;
		   case KeyEvent.VK_SHIFT:
			   
			   	 player.HUD.sprint = 1;
			   	 if(state == State.PLAYING){
				   	 if(player.xDirection == 2){player.xDirection = 1; player.checkImgDir();}
		        	 if(player.xDirection == -2){player.xDirection = -1; player.checkImgDir();}
		        	 if(player.yDirection == 2){player.yDirection = 1; player.checkImgDir();}
		        	 if(player.yDirection == -2){player.yDirection = -1; player.checkImgDir();}
			   	 }
			   break;
		   }      
	   }
	   
	   
	   // Process a key-typed event.
	   public void gameKeyTyped(char keyChar) {  }
	   
	   @SuppressWarnings("serial")
	// Custom drawing panel, written as an inner class.
	class GameCanvas extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
	      // Constructor
	      public GameCanvas() {
	         setFocusable(true);  // so that can receive key-events
	         requestFocus();
	         setResizable(false);
	         addKeyListener(this);
	         addMouseListener(this);
	         addMouseMotionListener(this);
	      }
	   
	      // Override paintComponent to do custom drawing.
	      // Called back by repaint().
	      
	      public void paintComponent(Graphics g) {
	         Graphics2D g2d = (Graphics2D)g;
	         super.paintComponent(g2d);   // paint background
	         setBackground(Color.BLACK);  // may use an image for background
	   
	         // Draw the game objects
	         gameDraw(g2d);
	      }
	      
	      // KeyEvent handlers
	      
	      public void keyPressed(KeyEvent e) {
	         gameKeyPressed(e.getKeyCode());
	      }
	      
	      
	      public void keyReleased(KeyEvent e) {
	         gameKeyReleased(e.getKeyCode());
	      }
	   
	      
	      public void keyTyped(KeyEvent e) {
	         gameKeyTyped(e.getKeyChar());
	      }

	      
	    public void mouseMoved(MouseEvent me){
	    	
	    	int x = me.getX();
			int y = me.getY();
			if(state == State.MENU && menu.section == 1){
				menu.hover = 0;
				if(x >= 500){
					menu.hover = 0;
					if( y >= 150 && y<200){
						menu.hover = 1;
					}
					if(y >= 200 && y<250 ){
						menu.hover = 2;
					}
					if(y>= 250 && y<300){
						menu.hover = 3;
					}
					if(y>=300 && y<350){
						menu.hover = 4;
					}
				}
			if(state == State.MENU && menu.section == 2){
				menu.hover = 0;
			}
					
				
			}
			//while playing do this stuff for signs
			if(state == State.PLAYING){
				//gets the block your mouse is in
				int mouseBlock = ((((y/20) * 40) ) + (x/20));
				//sets section to one less because section 4 is actually 3 for the array
				int s = lvl.playerInSec-1;
				//city 1
				if(lvl.playerInCity == 1){
					if(player.outside){
						if(lvl.playerInSec == 1){
							//sign positions
							if(lvl.city1[s][mouseBlock].isSign()){
								//purple house
								if(mouseBlock == 495){
									player.HUD.centerMessage("Purple House", 0, x+10, y);
								}
								//orange house
								if(mouseBlock == 505){
									player.HUD.centerMessage("Orange House", 0, x+10, y);
								}
								//red house
								if(mouseBlock == 895){
									player.HUD.centerMessage("Red House", 0, x+10, y);
								}
								//blue house
								if(mouseBlock == 905){
									player.HUD.centerMessage("Blue House", 0, x+10, y);
								}
							}else{
								//get rid of sign
								player.HUD.dispSignText = false;
							}
						}
						if(lvl.playerInSec == 2){
							if(lvl.city1[s][mouseBlock].isSign()){
								//bank
								if(mouseBlock == 496){
									player.HUD.centerMessage("Bank", 0, x+10, y);
								}
								//police station
								if(mouseBlock == 768){
									player.HUD.centerMessage("Police Station", 0, x+10, y);
								}
								//house 0
								if(mouseBlock == 909){
									player.HUD.centerMessage(lvl.ownerHouse[0] + "'s House", 0, x+10, y);
								}
								//house 1
								if(mouseBlock == 912){
									player.HUD.centerMessage(lvl.ownerHouse[1] + "'s House", 0, x+10, y);
								}
								//house 2
								if(mouseBlock == 1109){
									player.HUD.centerMessage(lvl.ownerHouse[2] + "'s House", 0, x+10, y);
								}
								//house 3
								if(mouseBlock == 1112){
									player.HUD.centerMessage(lvl.ownerHouse[3] + "'s House", 0, x+10, y);
								}
							}else{
								//remove sign
								player.HUD.dispSignText = false;
							}
						}
						if(lvl.playerInSec == 3){
							if(lvl.city1[s][mouseBlock].isSign()){
								//north school entrance
								if(mouseBlock == 488){
									player.HUD.centerMessage("University of Zebule", 0, x+10, y);
								}
								//south school entrance
								if(mouseBlock == 1024){
									player.HUD.centerMessage("University of Zebule Labratory", 0, x+10, y);
								}
							}else{
								//get rid of sign
								player.HUD.dispSignText = false;
							}
						}
						if(lvl.playerInSec == 4){
							if(lvl.city1[s][mouseBlock].isSign()){
								//bus station
								if(mouseBlock == 765){
									player.HUD.centerMessage("Bus Station", 0, x+10, y);
								}
								//hotel
								if(mouseBlock == 817){
									player.HUD.centerMessage("Hotel", 0, x+10, y);
								}
								//house 4
								if(mouseBlock == 204){
									player.HUD.centerMessage(lvl.ownerHouse[4] + "'s House", 0, x+10, y);
								}
								//house 5
								if(mouseBlock == 212){
									player.HUD.centerMessage(lvl.ownerHouse[5] + "'s House", 0, x+10, y);
								}
								//house 6
								if(mouseBlock == 225){
									player.HUD.centerMessage(lvl.ownerHouse[6] + "'s House", 0, x+10, y);
								}
								//house 7
								if(mouseBlock == 234){
									player.HUD.centerMessage(lvl.ownerHouse[7] + "'s House", 0, x+10, y);
								}
								//house 8
								if(mouseBlock == 345){
									player.HUD.centerMessage(lvl.ownerHouse[8] + "'s House", 0, x+10, y);
								}
								//house 9
								if(mouseBlock == 354){
									player.HUD.centerMessage(lvl.ownerHouse[9] + "'s House", 0, x+10, y);
								}
							}else{
								//get rid of sign
								player.HUD.dispSignText = false;
							}
						}
						if(lvl.playerInSec == 5){
							if(lvl.city1[s][mouseBlock].isSign()){
								//store
								if(mouseBlock == 377){
									player.HUD.centerMessage("Bargain Palace", 0, x+10, y);
								}
								//club
								if(mouseBlock == 383){
									player.HUD.centerMessage("Club Sizzle", 0, x+10, y);
								}
								//town hall
								if(mouseBlock == 776){
									player.HUD.centerMessage("Town Hall", 0, x+10, y);
								}
							}else{
								//get rid of sign
								player.HUD.dispSignText = false;
							}
						}
						if(lvl.playerInSec == 6){
							if(lvl.city1[s][mouseBlock].isSign()){
								//monastery south entrance
								if(mouseBlock == 808){
									player.HUD.centerMessage("Monastery South Wing", 0, x+10, y);
								}
								//monastery north entrance
								if(mouseBlock == 464){
									player.HUD.centerMessage("Monastery North Wing", 0, x+10, y);
								}
							}else{
								//get rid of sign
								player.HUD.dispSignText = false;
							}
						}
						if(lvl.playerInSec == 7){
							//change this if you have any more signs in this square
							//black smith hut
							if(lvl.city1[s][mouseBlock].isSign()){
								player.HUD.centerMessage("Black Smith's Hut", 0, x, y-50);
							}else{
								player.HUD.dispSignText = false;
							}
						}
						if(lvl.playerInSec == 8){
							//change this if you have any more signs in this square
							//lake sign
							if(lvl.city1[s][mouseBlock].isSign()){
								player.HUD.centerMessage("Lake Fallacy Home Of Nessy", 0, x+10, y);
							}else{
								player.HUD.dispSignText = false;
							}
						}
						if(lvl.playerInSec == 9){
							//change this if you have any more signs in this square
							//Mine
							if(lvl.city1[s][mouseBlock].isSign()){
								player.HUD.centerMessage("Mine", 0, x-100, y-50);
							}else{
								player.HUD.dispSignText = false;
							}
						}
					}
				}
			}
			//if mouse moves while in the inventory set position so you can draw item
			if(state == State.INVENTORY){
				player.HUD.dragLoc(x, y);
			}
		}
		public void mouseClicked(MouseEvent me) {
			int x = me.getX();
			int y = me.getY();
			if(state == State.MENU && menu.section == 1){
				if(x>=500){
					if(y >= 150 && y<200){
						state = State.LOAD;
					}
					if(y >= 200 && y < 250){
						menu.section = 2;
					}
					if(y >= 250 && y < 300){
						
					}
					if(y >= 300 && y < 350){
						state = State.DESTROYED;
						gameShutdown();
					}
				}
			}
			if(state == State.MENU && menu.section == 2){
				
			}
			if(state == State.PLAYING){
				//TODO shoot features
				if(x==y){
					//got rid of warnings. delete on code time.
				}
				if(me.getButton() == 1){
					player.HUD.cash += 100;
				}
				if(me.getButton() == 3){
					player.HUD.cash -= 100;
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
			if(state == State.INVENTORY){
				//if you click off it closes inventory and deletes any item in hand
				if(x > 700 || x < 100 || y > 500 || y < 100){
					if(player.HUD.inMouse != player.HUD.empty){
						player.HUD.inMouse = player.HUD.empty;
					}
					player.HUD.dispInv = false;
					state = State.PLAYING;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent me) {
			int x = me.getX();
			int y = me.getY();
			if(state == State.INVENTORY){
				//checks all spaces for swaps
				int ax=150,by=125;
				for(int i = 1; i < player.HUD.inventory.length; i++){
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
			player.HUD.TEMP_HOLD = player.HUD.inventory[loc];
			player.HUD.inventory[loc] = player.HUD.inMouse;
			player.HUD.inMouse = player.HUD.TEMP_HOLD;
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			
		}
	   }
	   
	   
	   public void log(String logmsg){
		   System.out.println(logmsg);
		   
	   }
	   
	   // main
	  /* public static void main(String[] args) {
	      // Use the event dispatch thread to build the UI for thread-safety.
	      SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	            new Main();
	         }
	      });
	   }*/
  
}