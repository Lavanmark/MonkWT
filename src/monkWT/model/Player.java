package monkWT.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Set;

import monkWT.model.levels.Levels;
import monkWT.view.HUD;
import resources.ResourceLoader;


public class Player {

	
	private Levels lvl;
	public HUD HUD = new HUD();
	
	private ResourceLoader rl = new ResourceLoader();
	
	private BufferedImage monkDown, monkUp, monkLeft, monkRight;
	
	// 0 = down 1 = up  2 = left  3 = right  
	public int pDir = 0;
	public Rectangle playerRect;
	public Rectangle playerLeft;
	public Rectangle playerRight;
	public Rectangle playerTop;
	public Rectangle playerFeet;
	
	public int currentCity;
	
	public boolean justChanged = false;
	public boolean sitting = false;
	private boolean sittingUp = false, sittingDown = false, sittingLeft = false, sittingRight = false, sittingHigh = false, sittingLow = false;
	public boolean moving = false;
	
	//character traits
	public int lives = 3;
	public String playerName = "Jeffery";
	
	public int xDirection = 0, yDirection = 0;
	private final int dColRH = 2, uColRH = 598, rColRH = 5, lColRH = 777;
	
	public Player(Levels l){
		lvl = l;
	}
	
	//initialize player variables
	public void playerInitialize(int whichCity){
		currentCity = whichCity;
		try{
			monkDown = rl.getSprite("monkDown.png");
			monkUp = rl.getSprite("monkUp.png");
			monkLeft = rl.getSprite("monkLeft.png");
			monkRight = rl.getSprite("monkRight.png");
		}catch(Exception e){
			System.err.println("character probs");
		}
		lvl.loadCity(currentCity);
		playerRect = new Rectangle(lvl.spawnRect.x, lvl.spawnRect.y, lvl.spawnRect.width, lvl.spawnRect.height);
        playerLeft = new Rectangle(lvl.spawnLeft.x, lvl.spawnLeft.y, lvl.spawnLeft.width, lvl.spawnLeft.height);
        playerRight = new Rectangle(lvl.spawnRight.x, lvl.spawnRight.y, lvl.spawnRight.width, lvl.spawnRight.height);
        playerTop = new Rectangle(lvl.spawnTop.x, lvl.spawnTop.y, lvl.spawnTop.width, lvl.spawnTop.height);
 		playerFeet = new Rectangle(lvl.spawnFeet.x, lvl.spawnFeet.y, lvl.spawnFeet.width, lvl.spawnFeet.height);
	}
	
	//updates numbers of deaths so collision will happen and it will be drawn
	public void setDeathsForColl(int dead){
		lvl.cityDeaths = dead;
		lvl.loadCity16();
	}
	//sets players position to where he should re-spawn wherever it was set
	public void playerRespawn(){
		playerRect.x = lvl.spawnRect.x;
		playerRect.y = lvl.spawnRect.y;
		playerLeft.x = lvl.spawnLeft.x;
		playerLeft.y = lvl.spawnLeft.y;
		playerRight.x = lvl.spawnRight.x;
		playerRight.y = lvl.spawnRight.y;
		playerTop.x = lvl.spawnTop.x;
		playerTop.y = lvl.spawnTop.y;
		playerFeet.x = lvl.spawnFeet.x;
		playerFeet.y = lvl.spawnFeet.y;
		
	}
	//sets the players location to the saved location.
	public void loadPosition(int x, int y){
		playerRect.x = x;
		playerRect.y = y;
		playerLeft.x = x;
		playerLeft.y = y;
		playerRight.x = x+15;
		playerRight.y = y;
		playerTop.x = x;
		playerTop.y = y;
		playerFeet.x = x;
		playerFeet.y = y+15;
	}
	
	//called when player is updated
	public void update(){
		//SPRINT
		sprintChecks();
		//Stealth updating
		stealthChecks();
		//Move the player + Collisions
		movePlayer();
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
		   int playerLocalLeftTop = (((((playerTop.y + yDirection)/20) * 40) ) + ((playerTop.x + xDirection)/20) ); 
		   int playerLocalRightTop = (((((playerTop.y + yDirection)/20) * 40) ) + ((playerTop.x + 14 + xDirection)/20) );
		   int playerLocalLeftBtm = (((((playerFeet.y + yDirection)/20) * 40) ) + ((playerFeet.x + xDirection)/20) ); 
		   int playerLocalRightBtm = (((((playerFeet.y + yDirection)/20) * 40) ) + ((playerFeet.x + 14 + xDirection)/20) );
		   
		   if(playerLocalLeftBtm > 1200){playerLocalLeftBtm -= 40;}
		   if(playerLocalRightBtm > 1200){playerLocalRightBtm -= 40;}
		   if(playerLocalLeftTop < 0){playerLocalLeftTop += 40;}
		   if(playerLocalRightTop < 0){playerLocalRightTop += 40;}
		   
		   if(lvl.getCityIn() == 1){
			   if(lvl.isPlayerInside()){
				   Set<Door> checkDoor = lvl.c1i.doorsInsideOne.get(lvl.c1i.getBuildingIn());
				   for(Door d : checkDoor){
					   if(d.getBlockLoc() == playerLocalLeftTop || d.getBlockLoc() == playerLocalRightTop 
							   || d.getBlockLoc() == playerLocalLeftBtm || d.getBlockLoc() == playerLocalRightBtm){
						   entBuilding(d.getBuildingEnt(),d.getxMoveDist(), d.getyMoveDist());
						   break;
					   }
				   }
			   }else{
				   Set<Door> checkDoor = lvl.doorsLvlOne.get(lvl.getSecIn());
				   for(Door d : checkDoor){
					   if(d.getBlockLoc() == playerLocalLeftTop || d.getBlockLoc() == playerLocalRightTop 
							   || d.getBlockLoc() == playerLocalLeftBtm || d.getBlockLoc() == playerLocalRightBtm){
						   entBuilding(d.getBuildingEnt(),d.getxMoveDist(), d.getyMoveDist());
						   stopSprint();
						   break;
					   }
				   }
			   }
		   }
	   }
	   
	   //stops the player from sprinting
	   private void stopSprint(){
		   HUD.sprint = 1;
		   HUD.dispSignText = false;
		   if(xDirection == 2){xDirection = 1;}
		   if(xDirection == -2){xDirection = -1;}
		   if(yDirection == 2){yDirection = 1;}
		   if(yDirection == -2){yDirection = -1;}
	   }
	   
	   private void sprintChecks(){
		   if(HUD.sprint == 2){
			   //if not moving but pressing sprint refill stamina
			   if(xDirection == 0 && yDirection == 0){   
				   if(HUD.sprintTime < 500){
					   //while the sprint time is less than 500 add one
					   HUD.sprintTime += 1;
				   }else if(HUD.sprintTime > 500){
					   //if the sprint time is over 500 stop it at 500
					   HUD.sprintTime = 500;
				   }
			   }else{
				   //if moving remove one from sprint time
				   HUD.sprintTime -= 1;
			   }
			   //if sprint time runs out then stop sprint and reset cool down time
			   if(HUD.sprintTime <= 0){
				   HUD.sprint = 1;
				   HUD.sprintCoolDown = 250;
			   }
		   }else{
			   //if you aren't sprinting and there is more cool down time then add some sprint time and take away cool down time
			   if(HUD.sprintCoolDown > 0){
				   HUD.sprintCoolDown -= 1;
				   HUD.sprintTime = -2*HUD.sprintCoolDown+500;
			   }else{
				   //add some if you aren't sprinting and your sprint meter isn't full
				   if(HUD.sprintTime < 500){
					   HUD.sprintTime += 1;
				   }else if(HUD.sprintTime > 500){
					   //if its more than 500 then set it to 500
					   HUD.sprintTime = 500;
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
		   if(HUD.sprint == 2 && moving){
			   HUD.stealthAmt = 0;
		   }
		   //use later to measure distance from people. Use with nearPersonStealthCheck();
		   int playerLocalLeftTop = (((((playerTop.y + yDirection)/20) * 40) ) + ((playerTop.x + xDirection)/20) ); 
		   int playerLocalRightTop = (((((playerTop.y + yDirection)/20) * 40) ) + ((playerTop.x + 14 + xDirection)/20) );
		   int playerLocalLeftBtm = (((((playerFeet.y + yDirection)/20) * 40) ) + ((playerFeet.x + xDirection)/20) ); 
		   int playerLocalRightBtm = (((((playerFeet.y + yDirection)/20) * 40) ) + ((playerFeet.x + 14 + xDirection)/20) );
		   
		   if(playerLocalLeftBtm > 1200){playerLocalLeftBtm -= 40;}
		   if(playerLocalRightBtm > 1200){playerLocalRightBtm -= 40;}
		   if(playerLocalLeftTop < 0){playerLocalLeftTop += 40;}
		   if(playerLocalRightTop < 0){playerLocalRightTop += 40;}
		   //walking takes away 1
		   if(moving){
			   if(HUD.stealthAmt > 0){
				   HUD.stealthAmt -= walkingPenalty;
			   }
		   }else{
			   //standing still adds 2
			   HUD.stealthAmt += sprintPenalty;
		   }
		   //sitting regenerates everything
		   if(sitting){
			   HUD.stealthAmt = 500;
		   }
		   //if stealth goes over or under then reset it
		   if(HUD.stealthAmt > 500){
			   HUD.stealthAmt = 500;
		   }else if(HUD.stealthAmt < 0){
			   HUD.stealthAmt = 0;
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
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void openInventory(){
		HUD.dispInv = true;
	   	HUD.dispSignText = false;
	   	if(HUD.inMouse != HUD.empty){
	   		//if bag is closed with object in hand then put it in first open one
			for(int i = 1; i < HUD.inventory.length; i++){
				if(HUD.inventory[i].getItemNum() == HUD.empty.getItemNum()){
					HUD.inventory[i] = HUD.inMouse;
				 	HUD.inMouse = HUD.empty;
				}
			}
			if(HUD.inMouse != HUD.empty && HUD.inventory[0] == HUD.empty){
			 	HUD.inventory[0] = HUD.inMouse;
			}
		}
	}
	public void closeInventory(){
		HUD.dispInv = false;
		if(HUD.inMouse != HUD.empty){
			//if bag is closed with object in hand then put it in first open one
			for(int i = 1; i < HUD.inventory.length; i++){
				if(HUD.inventory[i].getItemNum() == HUD.empty.getItemNum()){
					HUD.inventory[i] = HUD.inMouse;
					HUD.inMouse = HUD.empty;
				}
			}
			if(HUD.inMouse != HUD.empty && HUD.inventory[0] == HUD.empty){
				HUD.inventory[0] = HUD.inMouse;
			}
		}
	}
	
	
	//checks if the player is near a chair
	private boolean nearChair(int chairLoc){
		boolean is = false;
		int playerLocalLeftTop = ((((playerTop.y/20) * 40) ) + (playerTop.x/20) ); 
		int playerLocalRightTop = ((((playerTop.y/20) * 40) ) + ((playerTop.x + 14)/20) );
		int playerLocalLeftBtm = ((((playerFeet.y/20) * 40) ) + (playerFeet.x/20) ); 
		int playerLocalRightBtm = ((((playerFeet.y/20) * 40) ) + ((playerFeet.x + 14)/20) );
		
		if(playerLocalLeftTop-41 == chairLoc || playerLocalLeftTop-40 == chairLoc || playerLocalLeftTop-1 == chairLoc){
			is = true;
		}
		if(playerLocalLeftBtm+39 == chairLoc || playerLocalLeftBtm+40 == chairLoc || playerLocalRightBtm-1 == chairLoc){
			is = true;
		}
		if(playerLocalRightTop-40 == chairLoc || playerLocalRightTop-39 == chairLoc || playerLocalRightTop+1 == chairLoc){
			is = true;
		}
		if(playerLocalRightBtm+40 == chairLoc || playerLocalRightBtm+41 == chairLoc || playerLocalRightBtm+1 == chairLoc){
			is = true;
		}	
		return is;
	}
	
	//sits the player down based on variables
	private void makeSit(int dir, int sitDir, boolean extra, int x, int y){
		pDir = dir;
		setCharLoc(x,y);
		sitting = true;
		sittingHigh = extra;
		if(sitDir == 0){
			sittingDown = true;
		}else{
			sittingDown = false;
		}
		if(sitDir == 1){
			sittingUp = true;
		}else{
			sittingUp = false;
		}
		if(sitDir == 2){
			sittingLeft = true;
		}else{
			sittingLeft = false;
		}
		if(sitDir == 3){
			sittingRight = true;
		}else{
			sittingRight = false;
		}
	}
	private void makeSit(Chair chair){
		pDir = chair.getPlayerSitDir();
		setCharLoc(chair.getPlayerX(),chair.getPlayerY());
		sitting = true;
		sittingHigh = chair.isExtra();
		if(chair.getChairDir() == 0){
			sittingDown = true;
		}else{
			sittingDown = false;
		}
		if(chair.getChairDir() == 1){
			sittingUp = true;
		}else{
			sittingUp = false;
		}
		if(chair.getChairDir() == 2){
			sittingLeft = true;
		}else{
			sittingLeft = false;
		}
		if(chair.getChairDir() == 3){
			sittingRight = true;
		}else{
			sittingRight = false;
		}
	}
	
	//called when player presses the sit button
	public void checkChair(){
		System.out.println("checking chair");
		
		if(currentCity == 1){
			if(lvl.isPlayerInside()){
				//Building 12 : Large Orange House Left
				if(lvl.c1i.getBuildingIn() == 12){
					if(nearChair(420)){
						makeSit(0,0,false,402,204);
					}
				}
				//Building 13 : Large Orange House Right
				if(lvl.c1i.getBuildingIn() == 13){
					if(nearChair(420)){
						makeSit(0,0,false,402,204);
					}
				}
				//Building 14 : Small Orange House Top Left
				if(lvl.c1i.getBuildingIn() == 14){
					if(nearChair(420)){
						makeSit(0,0,false,402,204);
					}
				}
				//Building 15 : Small Orange House Top Right
				if(lvl.c1i.getBuildingIn() == 15){
					if(nearChair(420)){
						makeSit(0,0,false,402,204);
					}
				}
				//Building 16 : Small Orange House Bottom Left
				if(lvl.c1i.getBuildingIn() == 16){
					if(nearChair(540)){
						makeSit(1,1,false,402,264);
					}
				}
				//Building 17 : Small Orange House Bottom Right
				if(lvl.c1i.getBuildingIn() == 17){
					if(nearChair(540)){
						makeSit(1,1,false,402,264);
					}
				}
				//Building 18 : Bus Station
				
				if(lvl.c1i.getBuildingIn() == 18){
					//Inside Benches
					if(nearChair(821)){
						makeSit(1,1,false,424,400);
					}else if(nearChair(822)){
						makeSit(1,1,false,440,400);
					}
					if(nearChair(824)){
						makeSit(1,1,false,484,400);
					}else if(nearChair(825)){
						makeSit(1,1,false,500,400);
					}
					if(nearChair(741)){
						makeSit(0,0,false,424,362);
					}else if(nearChair(742)){
						makeSit(0,0,false,440,362);
					}
					if(nearChair(744)){
						makeSit(0,0,false,484,362);
					}else if(nearChair(745)){
						makeSit(0,0,false,500,362);
					}
					//TODO add when you sit on a bench it takes you to where ever you have a ticket to.
					//Outside Benches
					if(nearChair(533)){
						makeSit(2,2,false,262,264);
					}else if(nearChair(573)){
						makeSit(2,2,false,262,280);
					}
					if(nearChair(733)){
						makeSit(2,2,false,262,364);
					}else if(nearChair(773)){
						makeSit(2,2,false,262,380);
					}
				}
				//Building 19 : Hotel 
				/*if(lvl.c1i.getBuildingIn() == 19){
					//First Floor
					if(lvl.c1i.buildingSubSec == 1){
						//Lobby Couches
						if(nearChair(668)){
							makeSit(2,2,false,562,324);
						}else if(nearChair(708)){
							makeSit(2,2,false,562,340);
						}
						if(nearChair(788)){
							makeSit(2,2,false,562,384);
						}else if(nearChair(828)){
							makeSit(0,0,false,562,400);
						}
						//Room 101
						if(nearChair(1044)){
							makeSit(2,2,false,82,524);
						}else if(nearChair(1084)){
							makeSit(2,2,false,82,540);
						}
						//Room 103
						if(nearChair(1049)){
							makeSit(2,2,false,182,524);
						}else if(nearChair(1089)){
							makeSit(2,2,false,182,540);
						}
						//Room 105
						if(nearChair(1054)){
							makeSit(2,2,false,282,524);
						}else if(nearChair(1094)){
							makeSit(2,2,false,282,540);
						}
						//Room 107
						if(nearChair(1059)){
							makeSit(2,2,false,382,524);
						}else if(nearChair(1099)){
							makeSit(2,2,false,382,540);
						}
						//Room 109
						if(nearChair(1064)){
							makeSit(2,2,false,482,524);
						}else if(nearChair(1104)){
							makeSit(2,2,false,482,540);
						}
						//Room 111
						if(nearChair(1069)){
							makeSit(2,2,false,582,524);
						}else if(nearChair(1109)){
							makeSit(2,2,false,582,540);
						}
						//Room 113
						if(nearChair(1074)){
							makeSit(2,2,false,682,524);
						}else if(nearChair(1114)){
							makeSit(2,2,false,682,540);
						}
					}
					if(lvl.c1i.buildingSubSec == 2){
						//Room 202
						if(nearChair(650)){
							makeSit(2,2,false,202,324);
						}else if(nearChair(690)){
							makeSit(2,2,false,202,340);
						}
						if(nearChair(770)){
							makeSit(2,2,false,202,384);
						}else if(nearChair(810)){
							makeSit(2,2,false,202,400);
						}
						//Top chairs
						if(nearChair(645)){
							makeSit(0,2,false,102,324);
						}else if(nearChair(646)){
							makeSit(0,3,false,122,324);
						}
						//bottom chairs
						if(nearChair(765)){
							makeSit(1,2,false,102,380);
						}else if(nearChair(766)){
							makeSit(1,3,false,122,380);
						}
						//Room 206
						if(nearChair(659)){
							makeSit(2,2,false,382,324);
						}else if(nearChair(699)){
							makeSit(2,2,false,382,340);
						}
						if(nearChair(779)){
							makeSit(2,2,false,382,384);
						}else if(nearChair(819)){
							makeSit(2,2,false,382,400);
						}
						//Top chairs
						if(nearChair(655)){
							makeSit(0,2,false,302,324);
						}
						//bottom chairs
						if(nearChair(775)){
							makeSit(1,2,false,302,380);
						}else if(nearChair(776)){
							makeSit(1,3,false,322,380);
						}
						//Room 210
						if(nearChair(668)){
							makeSit(2,2,false,562,324);
						}else if(nearChair(708)){
							makeSit(2,2,false,562,340);
							//TODO change these to makeSit(2,2,false,182,524);
						}
						if(nearChair(788)){
							makeSit(2,2,false,562,384);
						}else if(nearChair(828)){
							makeSit(2,2,false,562,400);
						}
						//Top chairs
						if(nearChair(664)){
							makeSit(0,2,false,482,324);
						}else if(nearChair(665)){
							makeSit(0,3,false,502,324);
						}
						//bottom chairs
						if(nearChair(784)){
							makeSit(1,2,false,482,380);
						}else if(nearChair(785)){
							makeSit(1,3,false,502,380);
						}
						//Room 214
						if(nearChair(678)){
							makeSit(2,2,false,762,324);
						}else if(nearChair(718)){
							makeSit(2,2,false,762,340);
						}
						if(nearChair(798)){
							makeSit(2,2,false,762,384);
						}else if(nearChair(838)){
							makeSit(2,2,false,762,400);
						}
						//Top chairs
						if(nearChair(674)){
							makeSit(0,2,false,682,324);
						}else if(nearChair(675)){
							makeSit(0,3,false,702,324);
						}
						//bottom chairs
						if(nearChair(794)){
							makeSit(1,2,false,682,380);
						}else if(nearChair(795)){
							makeSit(1,3,false,702,380);
						}
						//Room 201
						if(nearChair(1044)){
							makeSit(2,2,false,82,524);
						}else if(nearChair(1084)){
							makeSit(2,2,false,82,540);
						}
						//Room 203
						if(nearChair(1049)){
							makeSit(2,2,false,182,524);
						}else if(nearChair(1089)){
							makeSit(2,2,false,182,540);
						}
						//Room 205
						if(nearChair(1054)){
							makeSit(2,2,false,282,524);
						}else if(nearChair(1094)){
							makeSit(2,2,false,282,540);
						}
						//Room 207
						if(nearChair(1059)){
							makeSit(2,2,false,382,524);
						}else if(nearChair(1099)){
							makeSit(2,2,false,382,540);
						}
						//Room 209
						if(nearChair(1064)){
							makeSit(2,2,false,482,524);
						}else if(nearChair(1104)){
							makeSit(2,2,false,482,540);
						}
						//Room 211
						if(nearChair(1069)){
							makeSit(2,2,false,582,524);
						}else if(nearChair(1109)){
							makeSit(2,2,false,582,540);
						}
						//Room 213
						if(nearChair(1074)){
							makeSit(2,2,false,682,524);
						}else if(nearChair(1114)){
							makeSit(2,2,false,682,540);
						}
					}
				}*/
				//Building 21 : Club
				if(lvl.c1i.getBuildingIn() == 21){
					//booths top left
					if(nearChair(206)){
						makeSit(3,0,false,120,100);
					}else if(nearChair(208)){
						makeSit(2,0,false,164,100);
					}else if(nearChair(166)){
						makeSit(3,0,true,120,84);
					}else if(nearChair(168)){
						makeSit(2,0,true,164,84);
					}
					//booths top middle
					if(nearChair(209)){
						makeSit(3,0,false,180,100);
					}else if(nearChair(211)){
						makeSit(2,0,false,224,100);
					}else if(nearChair(169)){
						makeSit(3,0,true,180,84);
					}else if(nearChair(171)){
						makeSit(2,0,true,224,84);
					}
					//TODO wrong positions
					//booths top middle
					if(nearChair(212)){
						makeSit(3,0,false,180,100);
					}else if(nearChair(214)){
						makeSit(2,0,false,224,100);
					}else if(nearChair(169)){
						makeSit(3,0,true,180,84);
					}else if(nearChair(171)){
						makeSit(2,0,true,224,84);
					}
					//TODO sitting isn't possible due to proximity in the booths or was it not changed yet??
					//TODO finish booths here and then bar stools
				}
				//TODO add monestary chairs. this is a pain in the butt. change how chairs work?
				
			}else{
				if(lvl.chairsLvlOne.containsKey(lvl.getSecIn())){
					System.out.println("found chair");
					Set<Chair> checkChair = lvl.chairsLvlOne.get(lvl.getSecIn());
					for(Chair c : checkChair){
						if(nearChair(c.getChairLoc())){
							makeSit(c);
							break;
						}
					}
				}
			}
		}
	}
	
	//called to check where it is sitting
	public void checkSitting(){
		if(sittingUp && !sittingHigh && !sittingLow){
			entMove(0,-20);
			sitting = false;
			sittingUp = false;
		}
		if(sittingDown && !sittingHigh && !sittingLow){
			entMove(0,20);
			sitting = false;
			sittingDown = false;
		}
		if(sittingLeft && !sittingHigh && !sittingLow){
			entMove(-20,0);
			sitting = false;
			sittingLeft = false;
		}
		if(sittingRight && !sittingHigh && !sittingLow){
			entMove(20,0);
			sitting = false;
			sittingRight = false;
		}
		if(sittingHigh){
			entMove(0,40);
			sitting = false;
			sittingHigh = false;
		}
		if(sittingLow){
			entMove(0,-40);
			sitting = false;
			sittingLow = false;
		}
	}
	
	//used for checking if a fence or other object is in place before moving across sections
	private boolean preCheck(int cit, int lev, int ret){
		boolean doIt = false;
		int playerLocalLeftTop = ((((playerTop.y/20) * 40) ) + (playerTop.x/20) ); 
		int playerLocalRightTop = ((((playerTop.y/20) * 40) ) + ((playerTop.x + 14)/20) );
		int playerLocalLeftBtm = ((((playerFeet.y/20) * 40) ) + (playerFeet.x/20) ); 
		int playerLocalRightBtm = ((((playerFeet.y/20) * 40) ) + ((playerFeet.x + 14)/20) );
		if(cit == 1){
			if(lev == 1){
				
			}
			if(lev == 2){
				
			}
			if(lev == 3){
				
			}
			if(lev == 4){
				
			}
			if(lev == 5){
				
			}
			if(lev == 6){
				if(ret == 3){
					if(lvl.city1[lev-1][playerLocalLeftTop].isSolid() || lvl.city1[lev-1][playerLocalLeftBtm].isSolid()
							|| lvl.city1[lev-1][playerLocalRightTop].isSolid() || lvl.city1[lev-1][playerLocalRightBtm].isSolid()){
						playerRect.y = uColRH - 15;//- height
						playerLeft.y = uColRH - 15;
						playerRight.y = uColRH - 15;
						playerTop.y = uColRH - 15;
						playerFeet.y = uColRH;
						lvl.setSecIn(3);
						lvl.setSecIn(3);
						doIt = true;
					}
				}
				if(ret == 5){
					if(lvl.city1[lev-1][playerLocalLeftTop].isSolid() || lvl.city1[lev-1][playerLocalLeftBtm].isSolid()
							|| lvl.city1[lev-1][playerLocalRightTop].isSolid() || lvl.city1[lev-1][playerLocalRightBtm].isSolid()){
						playerRect.x = lColRH;
						playerLeft.x = lColRH;
						playerRight.x = lColRH;
						playerTop.x = lColRH;
						playerFeet.x = lColRH;
						lvl.setSecIn(5);
						lvl.setSecIn(5);
						doIt = true;
					}
				}
				if(ret == 9){
					
				}
			}
			if(lev == 7){
				
			}
			if(lev == 8){
				
			}
			if(lev == 9){
				
			}
		}
		return doIt;
		
	}
	
	public void checkCollisionsOut(){
		int s = lvl.getSecIn()-1;
		
		int playerLocalLeftTop = ((((playerTop.y/20) * 40) ) + (playerTop.x/20) ); 
		int playerLocalRightTop = ((((playerTop.y/20) * 40) ) + ((playerTop.x + 14)/20) );
		int playerLocalLeftBtm = ((((playerFeet.y/20) * 40) ) + (playerFeet.x/20) ); 
		int playerLocalRightBtm = ((((playerFeet.y/20) * 40) ) + ((playerFeet.x + 14)/20) );
		
		if(playerLocalLeftBtm > 1200){playerLocalLeftBtm -= 40;}
		if(playerLocalRightBtm > 1200){playerLocalRightBtm -= 40;}
		if(playerLocalLeftTop < 0){playerLocalLeftTop += 40;}
		if(playerLocalRightTop < 0){playerLocalRightTop += 40;}
		
			if(currentCity == 1){
				if(!justChanged){
					if(!sitting){
						if((lvl.city1[s][playerLocalLeftTop].isDoor() || lvl.city1[s][playerLocalRightTop].isDoor() || lvl.city1[s][playerLocalLeftBtm].isDoor()
								|| lvl.city1[s][playerLocalRightBtm].isDoor())
								&& lvl.doorsLvlOne.containsKey(lvl.c1i.getBuildingIn())){
							checkDoor();
						}else{
							if(lvl.city1[s][playerLocalLeftTop].isSolid() || lvl.city1[s][playerLocalRightTop].isSolid() || lvl.city1[s][playerLocalLeftBtm].isSolid()
									|| lvl.city1[s][playerLocalRightBtm].isSolid()){
								if(xDirection == 1 || xDirection == 2){
									colLeftRight(-3);
								}else if(xDirection == -1 || xDirection == -2){
									colLeftRight(3);
								}
								if(yDirection == 1 || yDirection == 2){
									colUpDown(-3);
								}else if(yDirection == -1 || yDirection == -2){
									colUpDown(3);
								}
								if(lvl.city1[s][playerLocalLeftTop].isSolid() && lvl.city1[s][playerLocalRightTop].isSolid() && yDirection == 0){
									colUpDown(3);
								}
								if(lvl.city1[s][playerLocalLeftBtm].isSolid() && lvl.city1[s][playerLocalRightBtm].isSolid() && yDirection == 0){
									colUpDown(-3);
								}
								if(lvl.city1[s][playerLocalLeftTop].isSolid() && !lvl.city1[s][playerLocalRightTop].isSolid() && xDirection == 0 
										|| lvl.city1[s][playerLocalLeftBtm].isSolid() && !lvl.city1[s][playerLocalRightBtm].isSolid() && xDirection == 0){
									colLeftRight(3);
									
								}
								if(lvl.city1[s][playerLocalRightTop].isSolid() && !lvl.city1[s][playerLocalLeftTop].isSolid() && xDirection == 0 
										|| lvl.city1[s][playerLocalRightBtm].isSolid() && !lvl.city1[s][playerLocalLeftBtm].isSolid() && yDirection == 0){
									colLeftRight(-3);
								}
								
								System.out.println("collision");
								stopMoveChar();
							}
						}
					}
				}
				//top wall check
				if(playerTop.y < 2 && !justChanged){
					
					//if that then check for solid and if not solid move up section
					if(lvl.getSecIn() > 3){
						if(!lvl.city1[s][playerLocalLeftTop].isSolid() && !lvl.city1[s][playerLocalRightTop].isSolid()){
							lvl.setSecIn(lvl.getSecIn() - 3);
							justChanged = true;
							playerRect.y = uColRH - 15;//- height
							playerLeft.y = uColRH - 15;
							playerRight.y = uColRH - 15;
							playerTop.y = uColRH - 15;
							playerFeet.y = uColRH;
							justChanged = true;
							HUD.dispSignText = false;
						}else {
							
						}
					}
				}
				
				//bottom wall check
				if(playerFeet.y > 598 && (playerFeet.y+14) > 598 && !justChanged){
					//if that then check for solid and if not solid move down section
					if(lvl.getSecIn() < 7){
						if(!lvl.city1[s][playerLocalLeftBtm].isSolid() && !lvl.city1[s][playerLocalRightBtm].isSolid() && !justChanged){
							
							if(lvl.getSecIn() == 3){
								justChanged = true;
								playerRect.y = dColRH;
								playerLeft.y = dColRH;
								playerRight.y = dColRH;
								playerTop.y = dColRH;
								playerFeet.y = dColRH + 15;//+ height
								if(!preCheck(1,6,3)){
									lvl.setSecIn(lvl.getSecIn() + 3);
								}
							}else{
								lvl.setSecIn(lvl.getSecIn() + 3);
								justChanged = true;
								playerRect.y = dColRH;
								playerLeft.y = dColRH;
								playerRight.y = dColRH;
								playerTop.y = dColRH;
								playerFeet.y = dColRH + 15;//+ height
								justChanged = true;
								HUD.dispSignText = false;
							}
						}else{
							
						}
					}
				}
				//left wall check
				if(playerFeet.x < 2 && playerTop.x < 2 && !justChanged){
					
					//if that then check for solid and if not solid move down section
					if(lvl.getSecIn() != 1 && lvl.getSecIn() != 4 && lvl.getSecIn() != 7){
						if(!lvl.city1[s][playerLocalLeftBtm].isSolid() && !lvl.city1[s][playerLocalLeftTop].isSolid() && !justChanged){
							lvl.setSecIn(lvl.getSecIn() - 1);							
							playerRect.x = lColRH;
							playerLeft.x = lColRH;
							playerRight.x = lColRH;
							playerTop.x = lColRH;
							playerFeet.x = lColRH;
							justChanged = true;
							HUD.dispSignText = false;
						}else {
						
						}
					}
				}
				//right wall check
				if((playerFeet.x + 18) > 797 && (playerTop.x + 14) > 797 && !justChanged){
					
					//if that then check for solid and if not solid move down section
					
					//if you go to the bottom and you hit the right wall and bottom wall you get an error
					if(lvl.getSecIn() != 3 && lvl.getSecIn() != 6 && lvl.getSecIn() != 9){
						if(!lvl.city1[s][playerLocalRightBtm].isSolid() && !lvl.city1[s][playerLocalRightTop].isSolid() && !justChanged){

							if(lvl.getSecIn() == 5){
								justChanged = true;
								playerRect.x = rColRH;
								playerLeft.x = rColRH;
								playerRight.x = rColRH;
								playerTop.x = rColRH;
								playerFeet.x = rColRH;
								if(!preCheck(1,6,5)){
									lvl.setSecIn(lvl.getSecIn() + 1);
								}
							}else{
								lvl.setSecIn(lvl.getSecIn() + 1);
								justChanged = true;
								playerRect.x = rColRH;
								playerLeft.x = rColRH;
								playerRight.x = rColRH;
								playerTop.x = rColRH;
								playerFeet.x = rColRH;
								justChanged = true;
								HUD.dispSignText = false;
							}
						}else{
							
						}
					}
				}
			}
			
	
		
	}
	
	
	private void checkCollisionIn(){
		//TODO add the rest of the buildings
		//TODO optimize
		int playerLocalLeftTop = ((((playerTop.y/20) * 40) ) + (playerTop.x/20) ); 
		int playerLocalRightTop = ((((playerTop.y/20) * 40) ) + ((playerTop.x + 14)/20) );
		int playerLocalLeftBtm = ((((playerFeet.y/20) * 40) ) + (playerFeet.x/20) ); 
		int playerLocalRightBtm = ((((playerFeet.y/20) * 40) ) + ((playerFeet.x + 14)/20) );
		int s = lvl.c1i.getBuildingInArray();
			//Zebule
		if(currentCity == 1){
			if(!justChanged){
				if(!sitting){
					if(playerLocalLeftBtm > 1200 || playerLocalRightBtm > 1200){
						colUpDown(-3);
						playerLocalLeftTop = ((((playerTop.y/20) * 40) ) + (playerTop.x/20) ); 
						playerLocalRightTop = ((((playerTop.y/20) * 40) ) + ((playerTop.x + 14)/20) );
						playerLocalLeftBtm = ((((playerFeet.y/20) * 40) ) + (playerFeet.x/20) ); 
						playerLocalRightBtm = ((((playerFeet.y/20) * 40) ) + ((playerFeet.x + 14)/20) );
					}
					if(playerTop.y < 0){
						colUpDown(3);
						playerLocalLeftTop = ((((playerTop.y/20) * 40) ) + (playerTop.x/20) ); 
						playerLocalRightTop = ((((playerTop.y/20) * 40) ) + ((playerTop.x + 14)/20) );
						playerLocalLeftBtm = ((((playerFeet.y/20) * 40) ) + (playerFeet.x/20) ); 
						playerLocalRightBtm = ((((playerFeet.y/20) * 40) ) + ((playerFeet.x + 14)/20) );
					}
					if(playerLeft.x < 0){
						colLeftRight(3);
					}
					if((lvl.c1i.city1Inside[s][playerLocalLeftTop].isDoor() || lvl.c1i.city1Inside[s][playerLocalRightTop].isDoor() 
							|| lvl.c1i.city1Inside[s][playerLocalLeftBtm].isDoor() || lvl.c1i.city1Inside[s][playerLocalRightBtm].isDoor()) 
							&& lvl.c1i.doorsInsideOne.containsKey(lvl.c1i.getBuildingIn())){
						checkDoor();
					}else{
						if(lvl.c1i.city1Inside[s][playerLocalLeftTop].isSolid() || lvl.c1i.city1Inside[s][playerLocalRightTop].isSolid() 
								|| lvl.c1i.city1Inside[s][playerLocalLeftBtm].isSolid() || lvl.c1i.city1Inside[s][playerLocalRightBtm].isSolid()){
							if(xDirection == 1 || xDirection == 2){
								colLeftRight(-3);
							}else if(xDirection == -1 || xDirection == -2){
								colLeftRight(3);
							}
							if(yDirection == 1 || yDirection == 2){
								colUpDown(-3);
							}else if(yDirection == -1 || yDirection == -2){
								colUpDown(3);
							}
							if(lvl.c1i.city1Inside[s][playerLocalLeftTop].isSolid() && lvl.c1i.city1Inside[s][playerLocalRightTop].isSolid() && yDirection == 0){
								colUpDown(3);
							}
							if(lvl.c1i.city1Inside[s][playerLocalLeftBtm].isSolid() && lvl.c1i.city1Inside[s][playerLocalRightBtm].isSolid() && yDirection == 0){
								colUpDown(-3);
							}
							if(lvl.c1i.city1Inside[s][playerLocalLeftTop].isSolid() && !lvl.c1i.city1Inside[s][playerLocalRightTop].isSolid() && xDirection == 0 
									|| lvl.c1i.city1Inside[s][playerLocalLeftBtm].isSolid() && !lvl.c1i.city1Inside[s][playerLocalRightBtm].isSolid() && xDirection == 0){
								colLeftRight(3);
								
							}
							if(lvl.c1i.city1Inside[s][playerLocalRightTop].isSolid() && !lvl.c1i.city1Inside[s][playerLocalLeftTop].isSolid() && xDirection == 0 
									|| lvl.c1i.city1Inside[s][playerLocalRightBtm].isSolid() && !lvl.c1i.city1Inside[s][playerLocalLeftBtm].isSolid() && yDirection == 0){
								colLeftRight(-3);
							}
							
							System.out.println("collision inside");
							stopMoveChar();
						}
					}
				}
			}
		}
	}
	
	
	private void colLeftRight(int dir){
		playerRect.x += dir;
		playerLeft.x += dir;
		playerRight.x += dir;
		playerTop.x += dir;
		playerFeet.x += dir;
	}
	private void colUpDown(int dir){
		playerRect.y += dir;
		playerLeft.y += dir;
		playerRight.y += dir;
		playerTop.y += dir;
		playerFeet.y += dir;
	}
	
	public void movePlayer(){
		if(!lvl.isPlayerInside()){
			checkCollisionsOut();
		}else{
			checkCollisionIn();
		}
		playerRect.x += xDirection;
		playerRect.y += yDirection;
		playerLeft.x += xDirection;
		playerLeft.y += yDirection;
		playerRight.x += xDirection;
		playerRight.y += yDirection;
		playerTop.x += xDirection;
		playerTop.y += yDirection;
		playerFeet.x += xDirection;
		playerFeet.y += yDirection;
		if(xDirection != 0 || yDirection != 0){
			moving = true;
		}else if(xDirection == 0 && yDirection == 0){
			moving = false;
		}
		justChanged = false;
	}
	public void stopMoveChar(){
		xDirection = 0;
		yDirection = 0;
	}
	
	public void stopMoveCharX(){
		xDirection = 0;
	}
	public void stopMoveCharY(){
		yDirection = 0;
	}
	
	public void setXDirection(int dir){
		xDirection = dir;
	}
	
	public void setYDirection(int dir){
		yDirection = dir;
	}
	public void entMove(int xDist, int yDist){
		playerRect.x += xDist;
		playerLeft.x += xDist;
		playerRight.x += xDist;
		playerTop.x += xDist;
		playerFeet.x += xDist;
		playerRect.y += yDist;
		playerLeft.y += yDist;
		playerRight.y += yDist;
		playerTop.y += yDist;
		playerFeet.y += yDist;
	}
	public void setCharLoc(int x, int y){
		playerRect.x = x;
		playerLeft.x = x;
		playerRight.x = x+15;
		playerTop.x = x;
		playerFeet.x = x;
		playerRect.y = y;
		playerLeft.y = y;
		playerRight.y = y;
		playerTop.y = y;
		playerFeet.y = y+15;
	}
	public void entBuilding(int buildingNum, int xDist, int yDist){
		if(buildingNum == 0){
			lvl.setPlayerInside(false);
		}else{
			lvl.setPlayerInside(true);
			System.out.println(lvl.c1i.getBuildingIn());
			if(buildingNum == 20 || lvl.c1i.getBuildingIn() == 20){
				pDir = 1;
				stopMoveChar();
			}
		}
		lvl.setBuildingEnt(buildingNum);
		entMove(xDist,yDist);
	}
	
	
	static final int ONE_UP = 0, ONE_DOWN = 1, ONE_LEFT = 2, ONE_RIGHT = 3, TWO_UP = 4, TWO_DOWN = 5, TWO_LEFT = 6, TWO_RIGHT = 7, ACTION = 8;
	public void moveCharCase(int mve){
		switch(mve){
        default:
            System.out.println("default case entered... Doing nothing.");
            break;
        case ONE_UP:
            setYDirection(-1);
            break;
        case ONE_DOWN:
            setYDirection(1);
            break;
        case ONE_LEFT:
            setXDirection(-1);
            break;
        case ONE_RIGHT:
            setXDirection(1);
            break;
        case TWO_UP:
            setYDirection(-2);
            break;
        case TWO_DOWN:
            setYDirection(2);
            break;
        case TWO_LEFT:
            setXDirection(-2);
            break;
        case TWO_RIGHT:
            setXDirection(2);
            break;
        case ACTION:
        	
        	break;
		}
	}
	public void checkImgDir(){
		if(xDirection > 0 && pDir != 3){
			pDir = 3;
		}
		if(xDirection < 0 && pDir != 2){
			pDir = 2;
		}
		if(yDirection < 0 && pDir != 1){
			pDir = 1;
		}
		if(yDirection > 0 && pDir != 0){
			pDir = 0;
		}
	}
	public void draw(Graphics g){
		
		if(pDir == 0){
			if(HUD.inventory[0].getItemNum() != 0){
				g.drawImage(HUD.inventory[0].getPicDown(), playerRect.x+15-HUD.inventory[0].width-1, playerRect.y+15-2, null);
			}
			g.drawImage(monkDown, playerRect.x, playerRect.y, null);
		}
		if(pDir == 1){
			if(HUD.inventory[0].getItemNum() != 0){
				g.drawImage(HUD.inventory[0].getPicUp(), playerRect.x+HUD.inventory[0].width/2-1, playerRect.y-HUD.inventory[0].height+2, null);
			}
			g.drawImage(monkUp, playerRect.x, playerRect.y, null);
		}
		if(pDir == 2){
			if(HUD.inventory[0].getItemNum() != 0){
				g.drawImage(HUD.inventory[0].getPicLeft(), playerRect.x-HUD.inventory[0].height+2, playerRect.y+15-HUD.inventory[0].width-1, null);
			}
			g.drawImage(monkLeft, playerRect.x, playerRect.y, null);
		}
		if(pDir == 3){
			if(HUD.inventory[0].getItemNum() != 0){
				g.drawImage(HUD.inventory[0].getPicRight(), playerRect.x+15-2, playerRect.y+1, null);
			}
			g.drawImage(monkRight, playerRect.x, playerRect.y, null);
		}
	}
}
